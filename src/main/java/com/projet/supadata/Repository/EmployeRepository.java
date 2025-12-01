package com.projet.supadata.Repository;

import com.projet.supadata.Entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
    boolean existsByEmail(String email);

    Employe findEmployeByEmail(String email);
}
