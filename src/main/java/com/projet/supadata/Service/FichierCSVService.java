package com.projet.supadata.Service;

import com.projet.supadata.Entity.FichierCSV;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

public interface FichierCSVService {
    FichierCSV ajouterFichierCsv(Long entrepriseId , FichierCSV fichierCSV);

    List<FichierCSV> afficherFichierCsv();

    Optional<FichierCSV> afficherFichierCsvById(Long id );

    void importEmployeesFromExcel(MultipartFile file, Long entrepriseId);

    ByteArrayInputStream generateExcelTemplate();
}
