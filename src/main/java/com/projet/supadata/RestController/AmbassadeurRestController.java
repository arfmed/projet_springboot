package com.projet.supadata.RestController;
import com.projet.supadata.Entity.Ambassadeur;
import com.projet.supadata.Repository.AmbassadeurRepository;
import com.projet.supadata.Service.AmbassadeurService;
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
@RequestMapping(value ="/ambassadeur")
public class AmbassadeurRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    AmbassadeurRepository ambassadeurRepository;

    @Autowired
    AmbassadeurService ambassadeurService;


    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterAmbassadeur (@RequestBody Ambassadeur ambassadeur){

        HashMap<String, Object> response = new HashMap<>();
        if(ambassadeurRepository.existsByEmail(ambassadeur.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            ambassadeur.setMdp(this.bCryptPasswordEncoder.encode(ambassadeur.getMdp()));
            Ambassadeur savedUser = ambassadeurRepository.save(ambassadeur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Ambassadeur> AfficherAmbassadeur(){
        return ambassadeurService.afficherAmbassadeurs();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerAmbassadeur(@PathVariable("id") Long id){
        ambassadeurService.supprimerAmbassadeur(id);;

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Ambassadeur> getAmbassadeurById(@PathVariable("id") Long id){

        Optional<Ambassadeur> ambassadeur = ambassadeurService.afficherCarte(id);
        return ambassadeur;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Ambassadeur ModifierAAmbassadeur(@PathVariable("id")Long id, @RequestBody Ambassadeur ambassadeur){
        ambassadeur.setMdp(this.bCryptPasswordEncoder.encode(ambassadeur.getMdp()));
        Ambassadeur savedUser = ambassadeurRepository.save(ambassadeur);

        Ambassadeur newambassadeur = ambassadeurService.modifierAmbassadeur(ambassadeur);
        return newambassadeur;
    }




    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginambassadeur(@RequestBody Ambassadeur ambassadeur) {
        System.out.println("in login-ambassadeur"+ambassadeur);
        HashMap<String, Object> response = new HashMap<>();

        Ambassadeur userFromDB = ambassadeurRepository.findAmbassadeurByEmail(ambassadeur.getEmail());
        System.out.println("userFromDB+ambassadeur"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "ambassadeur not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(ambassadeur.getMdp(), userFromDB.getMdp());
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
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }
}
