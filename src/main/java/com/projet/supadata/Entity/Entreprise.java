package com.projet.supadata.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Entreprise
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String mdp;
    private String logoEntreprise;
    private String photoCouverture;
    private String urlEntreprise;
    private String adresse;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoEntreprise() {
        return logoEntreprise;
    }

    public void setLogoEntreprise(String logoEntreprise) {
        this.logoEntreprise = logoEntreprise;
    }

    public String getPhotoCouverture() {
        return photoCouverture;
    }

    public void setPhotoCouverture(String photoCouverture) {
        this.photoCouverture = photoCouverture;
    }

    public String getUrlEntreprise() {
        return urlEntreprise;
    }

    public void setUrlEntreprise(String urlEntreprise) {
        this.urlEntreprise = urlEntreprise;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
