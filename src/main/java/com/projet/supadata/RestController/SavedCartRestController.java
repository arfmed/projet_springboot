package com.projet.supadata.RestController;

import com.projet.supadata.Entity.SavedCart;
import com.projet.supadata.Repository.EmployeRepository;
import com.projet.supadata.Repository.ParticulierRepository;
import com.projet.supadata.Repository.SavedCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value ="/savedCart")
public class SavedCartRestController {
    @Autowired
    SavedCartRepository repo;

    @Autowired
    EmployeRepository employerRepository;
    @Autowired
    ParticulierRepository particulierRepository;

    @GetMapping("/my-cards")
    public ResponseEntity<?> getMyCards(@RequestParam Long particulierId) {
        List<SavedCart> savedList = repo.findByParticulierId(particulierId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (SavedCart card : savedList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", card.getId());
            map.put("particulierId", card.getParticulierId());
            map.put("employerId", card.getEmployeId());
            map.put("dateSave", card.getDateSave());

            // Fetch Employer details
            employerRepository.findById(card.getEmployeId()).ifPresent(emp -> {
                map.put("employerName", emp.getPrenom() + " " + emp.getNom());
                map.put("employerEmail", emp.getEmail());
                map.put("jobTitle", emp.getJobtitle());
                map.put("companyName", emp.getCompanyname());
                map.put("profilePicture", emp.getProfilepicture());
            });

            result.add(map);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SavedCart card) {

        boolean exists = repo.existsByParticulierIdAndEmployeId(
                card.getParticulierId(),
                card.getEmployeId());

        if (!exists) {
            repo.save(card);
        }

        return ResponseEntity.ok(Map.of("success", true));
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(
            @RequestParam Long particulierId,
            @RequestParam Long employeId) {

        boolean saved = repo.existsByParticulierIdAndEmployeId(
                particulierId, employeId);

        return ResponseEntity.ok(Map.of("saved", saved));
    }
    @GetMapping("/employer-cards")
    public ResponseEntity<?> getEmployeCards(
            @RequestParam Long employeId) {

        List<SavedCart> savedList =
                repo.findByEmployeId(employeId);

        return ResponseEntity.ok(savedList);
    }











    @GetMapping("/employers-cards")
    public ResponseEntity<?> getEmployesrCards(@RequestParam Long employeId) {

        List<SavedCart> list = repo.findByEmployeId(employeId);

        List<Map<String,Object>> result = new ArrayList<>();

        for(SavedCart sc : list){

            Map<String,Object> map = new HashMap<>();

            map.put("particulierId", sc.getParticulierId());
            map.put("employerId", sc.getEmployeId());
            map.put("dateSave", sc.getDateSave());

            // 👇 get particulier info
            particulierRepository.findById(sc.getParticulierId())
                    .ifPresent(p -> {
                        map.put("nom", p.getNom());
                        map.put("prenom", p.getPrenom());
                        map.put("email", p.getEmail());
                    });

            result.add(map);
        }

        return ResponseEntity.ok(result);
    }


}
