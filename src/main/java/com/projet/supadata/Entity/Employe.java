package com.projet.supadata.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
public class Employe {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoDeProfil;
    private String logoEntreprise;
    private String photoCouverture;
    private String titreEmploi;
    private String departement;
    private String nomEntreprise;
    private String numTelephone;
    private String urlEntreprise;
    private String adresse;
    private String couleur;
    private String police;
    private String fbLink;
    private String xLink;
    private String instagramLink;
    private String linkedinLink;
    private String youtubeLink;
    private String snapchatLink;
    private String tiktokLink;
    private String twitchLink;
    private String yelpLink;
    private String whatsappLink;
    private String signalLink;
    private String discordLink;
    private String skypeLink;
    private String telegramLink;
    private String githubLink;
    private String gitlabLink;
    private String nom;
    private String email;
    private String mdp;

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoDeProfil() {
        return photoDeProfil;
    }

    public void setPhotoDeProfil(String photoDeProfil) {
        this.photoDeProfil = photoDeProfil;
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

    public String getTitreEmploi() {
        return titreEmploi;
    }

    public void setTitreEmploi(String titreEmploi) {
        this.titreEmploi = titreEmploi;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
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

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getSnapchatLink() {
        return snapchatLink;
    }

    public void setSnapchatLink(String snapchatLink) {
        this.snapchatLink = snapchatLink;
    }

    public String getTiktokLink() {
        return tiktokLink;
    }

    public void setTiktokLink(String tiktokLink) {
        this.tiktokLink = tiktokLink;
    }

    public String getTwitchLink() {
        return twitchLink;
    }

    public void setTwitchLink(String twitchLink) {
        this.twitchLink = twitchLink;
    }

    public String getYelpLink() {
        return yelpLink;
    }

    public void setYelpLink(String yelpLink) {
        this.yelpLink = yelpLink;
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

    public String getDiscordLink() {
        return discordLink;
    }

    public void setDiscordLink(String discordLink) {
        this.discordLink = discordLink;
    }

    public String getSkypeLink() {
        return skypeLink;
    }

    public void setSkypeLink(String skypeLink) {
        this.skypeLink = skypeLink;
    }

    public String getTelegramLink() {
        return telegramLink;
    }

    public void setTelegramLink(String telegramLink) {
        this.telegramLink = telegramLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getGitlabLink() {
        return gitlabLink;
    }

    public void setGitlabLink(String gitlabLink) {
        this.gitlabLink = gitlabLink;
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
