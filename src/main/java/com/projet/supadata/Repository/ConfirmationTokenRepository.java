package com.projet.supadata.Repository;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByConfirmationToken(String confirmationemail);
}
