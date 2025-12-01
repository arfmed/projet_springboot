package com.projet.supadata.Service;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.Entreprise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {
    Entreprise ajouterEntreprise(Entreprise entreprise);
    Entreprise modifierEntreprise(Entreprise entreprise);
    void supprimerEnreprise(Long id);
    List<Entreprise> afficherEntreprises();
    Optional<Entreprise> afficherEntrepriseById(Long id);
}
