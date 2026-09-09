package com.projet.supadata.RestController;
import com.projet.supadata.Entity.Employe;
import com.projet.supadata.Entity.EmployeRequest;
import com.projet.supadata.Repository.EmployeRepository;
import com.projet.supadata.Service.EmailService;
import com.projet.supadata.Service.EmployeService;
import com.projet.supadata.Service.FileStorageService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value ="/employe")
public class EmployerResrController {

    @Autowired
    EmailService emailService;

    @Autowired
    FileStorageService fileStorageService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    EmployeService employeService;

    @PostMapping(
            value = "/ajouter",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> AjouterEmploye(
            @RequestPart("data") EmployeRequest dto,
            @RequestPart(required = false) MultipartFile profilepicture,
            @RequestPart(required = false) MultipartFile companylogo,
            @RequestPart(required = false) MultipartFile coverphoto
    ) throws IOException {

        HashMap<String, Object> response = new HashMap<>();

        if (employeRepository.existsByEmail(dto.getEmail())) {
            response.put("message", "Email existe déjà !");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Employe employe = new Employe();
        employe.setJobtitle(dto.getJobtitle());
        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        employe.setEmail(dto.getEmail());
        employe.setPwd(bCryptPasswordEncoder.encode(dto.getPwd()));
        employe.setDepartement(dto.getDepartement());
        employe.setCompanyname(dto.getCompanyname());
        employe.setTelephone(dto.getTelephone());
        employe.setUrlcompany(dto.getUrlcompany());
        employe.setAdresse(dto.getAdresse());
        employe.setColor(dto.getColor());
        employe.setPolice(dto.getPolice());
        employe.setFblink(dto.getFblink());

        employe.setLinkedinlink(dto.getLinkedinlink());

        employe.setGithublink(dto.getGithublink());

        // Images
        employe.setProfilepicture(
                fileStorageService.save(profilepicture, "employe")
        );
        employe.setCompanylogo(fileStorageService.save(companylogo, "employe"));
        employe.setCoverphoto(fileStorageService.save(coverphoto, "employe"));

        Employe saved = employeRepository.save(employe);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
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
        employe.setPwd(this.bCryptPasswordEncoder.encode(employe.getPwd()));
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
            boolean compare = this.bCryptPasswordEncoder.matches(employe.getPwd(), userFromDB.getPwd());
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
                response.put("premiereConnexion", userFromDB.isPremiereConnexion());   // <-- NOUVEAU
                response.put("id", userFromDB.getId());                                // <-- NOUVEAU
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }
    @PostMapping("/send-email")
    public ResponseEntity<Map<String, String>> sendEmail(
            @RequestBody Map<String, String> request
    ) {

        String particulierEmail = request.get("particulierEmail");
        String employerEmail = request.get("employerEmail");
        String employerName = request.get("employerName");
        String employerIdStr = request.get("employerId");

        Map<String, String> response = new HashMap<>();

        // Vérification email particulier
        if (particulierEmail == null || particulierEmail.trim().isEmpty()) {
            response.put("message", "Particulier email manquant");
            return ResponseEntity.badRequest().body(response);
        }

        // Vérification email employer
        if (employerEmail == null || employerEmail.trim().isEmpty()) {
            response.put("message", "Employer email manquant");
            return ResponseEntity.badRequest().body(response);
        }

        // Vérification nom employer
        if (employerName == null || employerName.trim().isEmpty()) {
            response.put("message", "Employer name manquant");
            return ResponseEntity.badRequest().body(response);
        }

        // Vérification ID employer
        if (employerIdStr == null || employerIdStr.trim().isEmpty()) {
            response.put("message", "Employer ID manquant");
            return ResponseEntity.badRequest().body(response);
        }

        Long employerId;

        try {
            employerId = Long.parseLong(employerIdStr);
        } catch (NumberFormatException e) {
            response.put("message", "Employer ID invalide");
            return ResponseEntity.badRequest().body(response);
        }

        emailService.sendCardEmail(
                particulierEmail,
                employerEmail,
                employerName,
                employerId
        );

        response.put("message", "Emails envoyés avec succès");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/entreprise/{id}")
    public List<Employe> getEmployesByEntreprise(@PathVariable("id") Long id) {

        return employeService.getEmployesByEntreprise(id);
    }
    @PutMapping(
            value = "/completer-profil/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> completerProfil(
            @PathVariable("id") Long id,
            @RequestPart("data") EmployeRequest dto,
            @RequestPart(required = false) MultipartFile profilepicture,
            @RequestPart(required = false) MultipartFile companylogo,
            @RequestPart(required = false) MultipartFile coverphoto
    ) throws IOException {

        String profilepicturePath = (profilepicture != null) ? fileStorageService.save(profilepicture, "employe") : null;
        String companylogoPath = (companylogo != null) ? fileStorageService.save(companylogo, "employe") : null;
        String coverphotoPath = (coverphoto != null) ? fileStorageService.save(coverphoto, "employe") : null;

        Employe updated = employeService.completerProfil(id, dto, profilepicturePath, companylogoPath, coverphotoPath);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
