package com.projet.supadata.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Entity
@Data
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Particulier.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(nullable = false, name = "particulier_id")
    private Particulier particulier;



    public ConfirmationToken(Particulier particulier) {
        this.particulier  = particulier;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public ConfirmationToken(Long tokenId, String confirmationToken, Date createdDate, Particulier particulier) {
        this.tokenId = tokenId;
        this.confirmationToken = confirmationToken;
        this.createdDate = createdDate;
        this.particulier = particulier;
    }

    public ConfirmationToken() {

    }
}
