package com.projet.supadata.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
public class Ambassadeur{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String PhotoDeProfil;
    private String logoEntreprise;
    private String PhotoCouverture;
    private String nomEntreprise;
    private String numTelephone;
    private String adresse;
    private String couleur;
    private String police;
    private String fbLink;
    private String xLink;
    private String whatsappLink;
    private String signalLink;
    private String telegramLink;
    private String discordLink;
    private String githubLink;
    private String nom;
    private String email;
    private String mdp;

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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoDeProfil() {
        return PhotoDeProfil;
    }

    public void setPhotoDeProfil(String photoDeProfil) {
        PhotoDeProfil = photoDeProfil;
    }

    public String getLogoEntreprise() {
        return logoEntreprise;
    }

    public void setLogoEntreprise(String logoEntreprise) {
        this.logoEntreprise = logoEntreprise;
    }

    public String getPhotoCouverture() {
        return PhotoCouverture;
    }

    public void setPhotoCouverture(String photoCouverture) {
        PhotoCouverture = photoCouverture;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getxLink() {
        return xLink;
    }

    public void setxLink(String xLink) {
        this.xLink = xLink;
    }

    public String getWhatsappLink() {
        return whatsappLink;
    }

    public void setWhatsappLink(String whatsappLink) {
        this.whatsappLink = whatsappLink;
    }

    public String getSignalLink() {
        return signalLink;
    }

    public void setSignalLink(String signalLink) {
        this.signalLink = signalLink;
    }

    public String getTelegramLink() {
        return telegramLink;
    }

    public void setTelegramLink(String telegramLink) {
        this.telegramLink = telegramLink;
    }

    public String getDiscordLink() {
        return discordLink;
    }

    public void setDiscordLink(String discordLink) {
        this.discordLink = discordLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }





}
