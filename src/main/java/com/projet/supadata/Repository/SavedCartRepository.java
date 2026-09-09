package com.projet.supadata.Repository;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.SavedCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedCartRepository extends JpaRepository<SavedCart,Long> {
    List<SavedCart> findByParticulierId(Long particulierId);

    boolean existsByParticulierIdAndEmployeId(Long particulierId, Long employeId);

    List<SavedCart> findByEmployeId(Long employerId);
}
