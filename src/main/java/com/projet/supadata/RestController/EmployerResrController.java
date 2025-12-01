package com.projet.supadata.RestController;

import com.projet.supadata.Entity.Employe;
import com.projet.supadata.Repository.EmployeRepository;
import com.projet.supadata.Service.EmployeService;
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
@RequestMapping(value ="/employe")
public class EmployerResrController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    EmployeService employeService;

    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterEmploye (@RequestBody Employe employe){

        HashMap<String, Object> response = new HashMap<>();
        if(employeRepository.existsByEmail(employe.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            employe.setMdp(this.bCryptPasswordEncoder.encode(employe.getMdp()));
            Employe savedUser = employeRepository.save(employe);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employe> AfficherEmploye(){
        return employeService.afficherEmploye();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerEmploye(@PathVariable("id") Long id){
        employeService.supprimerEmploye(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Employe> getEmployeById(@PathVariable("id") Long id){

        Optional<Employe> employe = employeService.afficherCarte(id);
        return employe;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Employe Modifieremploye(@PathVariable("id")Long id, @RequestBody Employe employe){
        employe.setMdp(this.bCryptPasswordEncoder.encode(employe.getMdp()));
        Employe savedUser = employeRepository.save(employe);
        Employe newemploye = employeService.ajouterEmploye(employe);
        return newemploye;
    }




    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginemploye(@RequestBody Employe employe) {
        System.out.println("in login-employe"+employe);
        HashMap<String, Object> response = new HashMap<>();

        Employe userFromDB = employeRepository.findEmployeByEmail(employe.getEmail());
        System.out.println("userFromDB+employe"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "employe not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(employe.getMdp(), userFromDB.getMdp());
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
