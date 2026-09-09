package com.projet.supadata.Service;

import com.projet.supadata.Entity.Contact;

import java.util.List;

public interface ContactService {
    Contact ajouterContact (Contact contact);

    List<Contact> AfficherContact();

    void supprimerContact(Long id);
}
