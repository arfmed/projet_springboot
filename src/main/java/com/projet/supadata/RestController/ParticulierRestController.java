package com.projet.supadata.RestController;
import com.projet.supadata.Entity.Particulier;
import com.projet.supadata.Repository.ParticulierRepository;
import com.projet.supadata.Service.ParticulierService;
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
@RequestMapping(value = "/particulier")
public class ParticulierRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    ParticulierRepository particulierRepository;

    @Autowired
    ParticulierService particulierService;

    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> Ajouterparticulier (@RequestBody Particulier particulier){

        HashMap<String, Object> response = new HashMap<>();
        if(particulierRepository.existsByEmail(particulier.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            particulier.setMdp(this.bCryptPasswordEncoder.encode(particulier.getMdp()));
            Particulier savedUser = particulierRepository.save(particulier);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Particulier> Afficherparticulier(){
        return particulierService.afficherParticulier();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void Supprimerparticulier(@PathVariable("id") Long id){
        particulierService.supprimerParticulier(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Particulier> getparticulierById(@PathVariable("id") Long id){

        Optional<Particulier> particulier = particulierService.afficherCarte(id);
        return particulier;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Particulier Modifierparticulier(@PathVariable("id")Long id, @RequestBody Particulier particulier){
        particulier.setMdp(this.bCryptPasswordEncoder.encode(particulier.getMdp()));
        Particulier savedUser = particulierRepository.save(particulier);

        Particulier newparticulier = particulierService.modifierParticulier(particulier);
        return newparticulier;
    }




    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginparticulier(@RequestBody Particulier particulier) {
        System.out.println("in login-particulier"+particulier);
        HashMap<String, Object> response = new HashMap<>();

        Particulier userFromDB = particulierRepository.findParticulierByEmail(particulier.getEmail());
        System.out.println("userFromDB+particulier"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "particulier not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(particulier.getMdp(), userFromDB.getMdp());
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
