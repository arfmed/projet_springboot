package com.projet.supadata.Service;

import com.projet.supadata.Entity.Entreprise;
import com.projet.supadata.Repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntrepriseServiceImpl implements EntrepriseService{

    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Override
    public Entreprise ajouterEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise modifierEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public void supprimerEnreprise(Long id) {
        entrepriseRepository.deleteById(id);
    }

    @Override
    public List<Entreprise> afficherEntreprises() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Optional<Entreprise> afficherEntrepriseById(Long id) {
        return entrepriseRepository.findById(id);
    }
}
