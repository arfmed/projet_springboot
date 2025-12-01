package com.projet.supadata.RestController;


import com.projet.supadata.Entity.Entreprise;
import com.projet.supadata.Repository.EntrepriseRepository;
import com.projet.supadata.Service.EntrepriseService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value ="/entreprise")

public class EntrepriseRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EntrepriseRepository entrepriseRepository;

    @Autowired
    EntrepriseService entrepriseService;

    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterEntreprise (@RequestBody Entreprise entreprise){

        HashMap<String, Object> response = new HashMap<>();
        if(entrepriseRepository.existsByEmail(entreprise.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            entreprise.setMdp(this.bCryptPasswordEncoder.encode(entreprise.getMdp()));
            Entreprise savedUser = entrepriseRepository.save(entreprise);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Entreprise> AfficherEntreprise(){
        return entrepriseService.afficherEntreprises();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerEntreprise(@PathVariable("id") Long id){
        entrepriseService.supprimerEnreprise(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Entreprise> getEntrepriseById(@PathVariable("id") Long id){

        Optional<Entreprise> entreprise = entrepriseService.afficherEntrepriseById(id);
        return entreprise;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Entreprise ModifierEntreprise(@PathVariable("id")Long id, @RequestBody Entreprise entreprise){
        entreprise.setMdp(this.bCryptPasswordEncoder.encode(entreprise.getMdp()));
        Entreprise savedUser = entrepriseRepository.save(entreprise);

        Entreprise newEntreprise = entrepriseService.modifierEntreprise(entreprise);
        return newEntreprise;
    }




    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginEntreprise(@RequestBody Entreprise entreprise) {
        System.out.println("in login-entreprise"+entreprise);
        HashMap<String, Object> response = new HashMap<>();

        Entreprise userFromDB = entrepriseRepository.findEntrepriseByEmail(entreprise.getEmail());
        System.out.println("userFromDB+entreprise"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "entreprise not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(entreprise.getMdp(), userFromDB.getMdp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "Password incorrect!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                //response.put("role", userFromDB.getRole());
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }

}
