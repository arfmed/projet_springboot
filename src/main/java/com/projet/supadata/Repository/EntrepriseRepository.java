package com.projet.supadata.Repository;
import com.projet.supadata.Entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {


    boolean existsByEmail(String email);

    Entreprise findEntrepriseByEmail(String email);
}
