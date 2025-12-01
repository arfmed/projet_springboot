package com.projet.supadata.Repository;
import com.projet.supadata.Entity.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticulierRepository extends JpaRepository <Particulier,Long>{
    boolean existsByEmail(String email);

    Particulier findParticulierByEmail(String email);
}
