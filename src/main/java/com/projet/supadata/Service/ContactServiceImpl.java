package com.projet.supadata.Service;

import com.projet.supadata.Entity.Admin;
import com.projet.supadata.Entity.Contact;
import com.projet.supadata.Repository.AdminRepository;
import com.projet.supadata.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact ajouterContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> AfficherContact() {
        return contactRepository.findAll();
    }

    @Override
    public void supprimerContact(Long id) {
        contactRepository.deleteById(id);

    }
}
