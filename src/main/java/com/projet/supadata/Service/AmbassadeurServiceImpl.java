package com.projet.supadata.Service;

import com.projet.supadata.Entity.Ambassadeur;
import com.projet.supadata.Repository.AmbassadeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AmbassadeurServiceImpl implements AmbassadeurService{
    @Autowired
    AmbassadeurRepository ambassadeurRepository;
    @Override
    public Ambassadeur ajouterAmbassadeur(Ambassadeur ambassadeur) {
        return ambassadeurRepository.save(ambassadeur);
    }

    @Override
    public Ambassadeur modifierAmbassadeur(Ambassadeur ambassadeur) {
        return ambassadeurRepository.save(ambassadeur);
    }

    @Override
    public void supprimerAmbassadeur(Long id) {
        ambassadeurRepository.deleteById(id);
    }

    @Override
    public List<Ambassadeur> afficherAmbassadeurs() {
        return ambassadeurRepository.findAll();
    }

    @Override
    public Optional<Ambassadeur> afficherCarte(Long id) {
        return ambassadeurRepository.findById(id);
    }
}
