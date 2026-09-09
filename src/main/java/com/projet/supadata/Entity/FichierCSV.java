package com.projet.supadata.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class FichierCSV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String fichier;
    public String filename;
    public String filepath;
    public LocalDateTime uploadDate;
    @ManyToOne
    private Entreprise entreprise;
}
