package com.projet.supadata.Service;
import com.projet.supadata.Entity.Employe;
import com.projet.supadata.Entity.EmployeRequest;
import com.projet.supadata.Repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public List<Employe> getEmployesByEntreprise(Long id) {
        return employeRepository.findByEntrepriseId(id) ;
    }

    @Override
    public Employe completerProfil(Long id, EmployeRequest dto, String profilepicturePath, String companylogoPath, String coverphotoPath) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));

        employe.setJobtitle(dto.getJobtitle());
        employe.setDepartement(dto.getDepartement());
        employe.setCompanyname(dto.getCompanyname());
        employe.setTelephone(dto.getTelephone());
        employe.setUrlcompany(dto.getUrlcompany());

        employe.setColor(dto.getColor());
        employe.setPolice(dto.getPolice());
        employe.setFblink(dto.getFblink());
        employe.setLinkedinlink(dto.getLinkedinlink());
        employe.setGithublink(dto.getGithublink());

        if (dto.getPwd() != null && !dto.getPwd().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            employe.setPwd(encoder.encode(dto.getPwd()));
        }

        if (profilepicturePath != null) employe.setProfilepicture(profilepicturePath);
        if (companylogoPath != null) employe.setCompanylogo(companylogoPath);
        if (coverphotoPath != null) employe.setCoverphoto(coverphotoPath);

        employe.setPremiereConnexion(false);

        return employeRepository.save(employe);
    }
}
