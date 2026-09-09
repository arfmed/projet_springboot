package com.projet.supadata.RestController;

import com.projet.supadata.Entity.Contact;
import com.projet.supadata.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping(value ="/contact")
public class ContactRestController {
    @Autowired
    ContactService contactService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> AfficherContact(){

        return contactService.AfficherContact();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerContact(@PathVariable("id") Long id){
        contactService.supprimerContact(id);

    }

    @RequestMapping(method = RequestMethod.POST)
    public Contact AjouterContact(
            @RequestBody Contact contact){
        return contactService.ajouterContact(contact);
    }
}
