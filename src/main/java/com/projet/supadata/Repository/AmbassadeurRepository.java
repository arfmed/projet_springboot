package com.projet.supadata.Repository;

import com.projet.supadata.Entity.Ambassadeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbassadeurRepository extends JpaRepository<Ambassadeur,Long> {

    boolean existsByEmail(String email);

    Ambassadeur findAmbassadeurByEmail(String email);
}
