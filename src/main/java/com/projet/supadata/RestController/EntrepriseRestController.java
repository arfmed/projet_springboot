package com.projet.supadata.RestController;



import com.projet.supadata.Entity.Entreprise;
import com.projet.supadata.Entity.EntrepriseRequest;
import com.projet.supadata.Repository.EntrepriseRepository;
import com.projet.supadata.Service.EmailService;
import com.projet.supadata.Service.EntrepriseService;
import com.projet.supadata.Service.FileStorageService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
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
@RequestMapping(value ="/entreprise")

public class EntrepriseRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmailService emailService;

    @Autowired
    EntrepriseRepository entrepriseRepository;

    @Autowired
    EntrepriseService entrepriseService;
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping(
            value = "/ajouter",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> AjouterEntreprise(
            @RequestPart("data") EntrepriseRequest dto,
            @RequestPart(required = false) MultipartFile profilepicture,
            @RequestPart(required = false) MultipartFile companylogo,
            @RequestPart(required = false) MultipartFile coverphoto
    ) throws IOException {

        HashMap<String, Object> response = new HashMap<>();

        if (entrepriseRepository.existsByEmail(dto.getEmail())) {
            response.put("message", "Email existe déjà !");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Entreprise entreprise = new Entreprise();
        entreprise.setJobtitle(dto.getJobtitle());
        entreprise.setNom(dto.getNom());
        entreprise.setEmail(dto.getEmail());
        entreprise.setMdp(bCryptPasswordEncoder.encode(dto.getMdp()));
        entreprise.setDepartement(dto.getDepartement());
        entreprise.setCompanyname(dto.getCompanyname());
        entreprise.setTelephone(dto.getTelephone());
        entreprise.setUrlcompany(dto.getUrlcompany());
        entreprise.setAdresse(dto.getAdresse());
        entreprise.setColor(dto.getColor());
        entreprise.setPolice(dto.getPolice());
        entreprise.setFblink(dto.getFblink());

        entreprise.setLinkedinlink(dto.getLinkedinlink());

        entreprise.setGithublink(dto.getGithublink());

        // Images
        entreprise.setProfilepicture(fileStorageService.save(profilepicture, "entreprise"));
        entreprise.setCompanylogo(fileStorageService.save(companylogo, "entreprise"));
        entreprise.setCoverphoto(fileStorageService.save(coverphoto, "entreprise"));
            Entreprise savedUser = entrepriseRepository.save(entreprise);

            // Send verification email after successful registration
            String subject = "Bienvenue - Vérification de votre compte";
            String text = "Votre compte ResponsableEntreprise a été créé avec succès!\n\n"
                    + "Email: " + savedUser.getEmail() + "\n"
                    + "Nom: " + savedUser.getNom() + "\n\n"
                    + "Veuillez attendre la validation de votre compte par l'administrateur.";
            emailService.SendSimpleMessage(savedUser.getEmail(), subject, text);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
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
            }
            else {
                if (userFromDB.isEtat()==true) {
                    String token = Jwts.builder()
                            .claim("data", userFromDB)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();
                    response.put("token", token);

                    System.out.println("hhh");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
            }
        }
    }
    @PutMapping(value = "/updateetat/{id}")
    public ResponseEntity<Map<String, Object>> modifieretatRespoEntreprise(@RequestBody Entreprise entreprise, @PathVariable("id") Long id) {
        Entreprise newResponsableEntreprise = null;
        HashMap<String,Object>response=new HashMap<>();
        if (entrepriseRepository.findById(id).isPresent()) { //ken user deja mawjoud
            Entreprise entreprise1 = entrepriseRepository.findById(id).get();
            var entrepriseid = entreprise.getId();
            var nom = entreprise.getNom();

            var telephone =entreprise.getTelephone();
            var email = entreprise.getEmail();
            var mdp = entreprise.getMdp();
            var profilepicture = entreprise.getProfilepicture();
            var companyLogo = entreprise.getCompanylogo();
            var coverPhoto = entreprise.getCoverphoto();
            var jobtitle = entreprise.getJobtitle();
            var departement = entreprise.getDepartement();
            var companyName = entreprise.getCompanyname();
            var urlcompany = entreprise.getUrlcompany();
            var adresse = entreprise.getAdresse();
            var color = entreprise.getColor();
            var police= entreprise.getPolice();
            var fbLink =entreprise.getFblink();
            var linkedinLink = entreprise.getLinkedinlink();
            var githubLink = entreprise.getGithublink();



            entreprise1.setId(entrepriseid);
            entreprise1.setNom(nom);

            entreprise1.setTelephone(telephone);
            entreprise1.setEmail(email);
            entreprise1.setMdp(mdp);
            entreprise1.setProfilepicture(profilepicture);
            entreprise1.setCompanylogo(companyLogo);
            entreprise1.setCoverphoto(coverPhoto);
            entreprise1.setPolice(police);
            entreprise1.setJobtitle(jobtitle);
            entreprise1.setLinkedinlink(linkedinLink);
            entreprise1.setGithublink(githubLink);
            entreprise1.setFblink(fbLink);
            entreprise1.setAdresse(adresse);
            entreprise1.setDepartement(departement);
            entreprise1.setUrlcompany(urlcompany);
            entreprise1.setColor(color);
            entreprise1.setCompanyname(companyName);


//mta3 yjih mail fih l etat
            entreprise.setMdp(this.bCryptPasswordEncoder.encode(entreprise1.getMdp()));
            if (entreprise.isEtat() != entreprise1.isEtat()) {
                String etat = entreprise1.isEtat() ? "<strong ><span style=\"color: red;\">Bloqué</span>\n</strong>" : "<strong><span style=\"color: green;\">Accepté</span>\n</strong>";
                String loginLink = "";

                String logoImagePath = "cid:logoImage";
                String messageHTML =
                        "<!DOCTYPE html>" +
                                "<html>" +
                                "<head>" +
                                "<style>" +
                                ".card {" +
                                "   background-color: #f9f9f9;" +
                                "   border-radius: 10px;" +
                                "   padding: 20px;" +
                                "   margin: 20px auto;" +
                                "   width: 400px;" +
                                "   box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);" +
                                "}" +
                                ".logo {" +
                                "   text-align: center;" +
                                "   margin-bottom: 20px;" +
                                "}" +
                                ".logo img {" +
                                "   max-width: 200px;" +
                                "}" +
                                ".button {" +
                                "   display: block;" +
                                "   width: 200px;" +
                                "   margin: 0 auto;" +
                                "   padding: 10px 20px;" +
                                "   background-color: #7f62fb;" +
                                "   color: white;" +
                                "   text-decoration: none;" +
                                "   text-align: center;" +
                                "   border-radius: 5px;" +
                                "   font-size: 16px;" +
                                "}" +
                                "</style>" +
                                "</head>" +
                                "<body>" +
                                "<div class=\"card\">" +
                                "<div class=\"logo\">" +
                                "<img src=\"cid:logoImage\" alt=\"Your Logo\">" +
                                "</div>" +
                                "<p> Salut <strong>" + entreprise.getNom() + "</strong>" +
                                "<h2>État de votre compte</h2>" +
                                "<h4>Votre compte a été " + etat + "</h4>";

                if (entreprise.isEtat()) { // If state is accepted
                    messageHTML += "<p>Cliquez ci-dessous pour revenir à la page de connexion :</p>\n" +
                            "<a href=\"http://localhost:4200/\"><button class=button>Connexion</button></a>\n";
                }

                messageHTML += "</div>" +
                        "</body>" +
                        "</html>";

                MimeMessage message = emailService.createMimeMessage();
                MimeMessageHelper helper;
                try {
                    helper = new MimeMessageHelper(message, true);
                    helper.setTo(entreprise.getEmail());
                    helper.setSubject("Acceptation inscription !");
                    helper.setText(messageHTML, true);
                    helper.addInline("logoImage", new ClassPathResource("static/images/logoad.png"));
                    emailService.SendEmail(message);
                } catch (MessagingException e) {

                }

            }

            entreprise1.setEtat(entreprise.isEtat());

            entreprise = entrepriseRepository.save(entreprise1);
           /* String token = Jwts.builder()
                    .claim("data", newentreprise)
                    .signWith(SignatureAlgorithm.HS256, "SECRET")
                    .compact();

            response.put("Médecin", newentreprise);*/
            String token = Jwts.builder()
                    .setSubject(entreprise.getEmail())
                    .claim("id", entreprise.getId())
                    .claim("nom", entreprise.getNom())
                    .claim("etat", entreprise.isEtat())
                    .signWith(SignatureAlgorithm.HS256, "SECRET")
                    .compact();

            response.put("Entreprise", entreprise);

            response.put("token", token);
            System.out.println("ddddddddddddd");

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            response.put("message", "Entreprise not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }}

}
