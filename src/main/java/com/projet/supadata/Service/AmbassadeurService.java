package com.projet.supadata.Service;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.Ambassadeur;

import java.util.List;
import java.util.Optional;

public interface AmbassadeurService {
    Ambassadeur ajouterAmbassadeur(Ambassadeur ambassadeur);
    Ambassadeur modifierAmbassadeur(Ambassadeur ambassadeur);
    void supprimerAmbassadeur(Long id);
    List<Ambassadeur> afficherAmbassadeurs();
    Optional<Ambassadeur> afficherCarte(Long id);
}
