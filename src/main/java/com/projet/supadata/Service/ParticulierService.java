package com.projet.supadata.Service;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.Particulier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ParticulierService {
    Particulier ajouterParticulier(Particulier particulier);
    Particulier modifierParticulier(Particulier Particulier);
    void supprimerParticulier(Long id);
    List<Particulier> afficherParticulier();
    Optional<Particulier> afficherCarte(Long id);
}
