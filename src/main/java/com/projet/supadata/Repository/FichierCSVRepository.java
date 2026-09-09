package com.projet.supadata.Repository;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.FichierCSV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierCSVRepository extends JpaRepository<FichierCSV,Long> {
}
