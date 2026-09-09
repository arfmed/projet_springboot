package com.projet.supadata.Service;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.Employe;
import com.projet.supadata.Entity.EmployeRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Employe ajouterEmploye(Employe employe);
    Employe modifierEmploye(Employe employe);
    void supprimerEmploye(Long id);
    List<Employe> afficherEmploye();
    Optional<Employe> afficherCarte(Long id);
    List<Employe> getEmployesByEntreprise(Long id);
    Employe completerProfil(Long id, EmployeRequest dto, String profilepicturePath, String companylogoPath, String coverphotoPath); //NOUVEAU
}
