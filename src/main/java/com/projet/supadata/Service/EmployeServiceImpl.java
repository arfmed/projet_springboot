package com.projet.supadata.Service;
import com.projet.supadata.Entity.Employe;
import com.projet.supadata.Repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeServiceImpl implements EmployeService{
    @Autowired
    EmployeRepository employeRepository;
    @Override
    public Employe ajouterEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifierEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public void supprimerEmploye(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public List<Employe> afficherEmploye() {
        return employeRepository.findAll();
    }

    @Override
    public Optional<Employe> afficherCarte(Long id) {
        return employeRepository.findById(id);
    }
}
