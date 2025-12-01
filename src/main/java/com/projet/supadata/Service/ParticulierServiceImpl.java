package com.projet.supadata.Service;

import com.projet.supadata.Entity.Particulier;
import com.projet.supadata.Repository.ParticulierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ParticulierServiceImpl implements ParticulierService{

    @Autowired
    ParticulierRepository particulierRepository;
    @Override
    public Particulier ajouterParticulier(Particulier particulier) {
        return particulierRepository.save(particulier);
    }

    @Override
    public Particulier modifierParticulier(Particulier particulier) {
        return particulierRepository.save(particulier);
    }

    @Override
    public void supprimerParticulier(Long id) {
        particulierRepository.deleteById(id);

    }

    @Override
    public List<Particulier> afficherParticulier() {
        return particulierRepository.findAll();
    }

    @Override
    public Optional<Particulier> afficherCarte(Long id) {
        return particulierRepository.findById(id);
    }
}
