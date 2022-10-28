package net.yorksolutions.contactsspringboot.services;

import net.yorksolutions.contactsspringboot.models.Contact;
import net.yorksolutions.contactsspringboot.repositories.CompanyRepository;
import net.yorksolutions.contactsspringboot.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ContactService {

    ContactRepository repo;

    public ContactService(ContactRepository repo, CompanyRepository companyRepo) {
        this.repo = repo;
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> myContacts = new ArrayList<>();
        Iterable<Contact> found = this.repo.findAll();

        for(Contact contact : found)
        {
            myContacts.add(contact);
        }
        return myContacts;
    }

    public Optional<Contact> getContactByFirstName(String firstName){
        return this.repo.findByFirstName(firstName);
    }
    public Optional<Contact> getContactById(long id){
        return this.repo.findById(id);
    }
    public void addContact(Contact newContact) {
        this.repo.save(newContact);
    }

    public boolean deleteContact(long id) {
        boolean exists = this.repo.existsById(id);
        if(exists){
            this.repo.findById(id).orElseThrow().dismissCompany();
            this.repo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public boolean updateContact(long id, Contact updatedContact) {
        boolean exists = this.repo.existsById(id);
        if(exists){
            updatedContact.id = id;
            this.repo.save(updatedContact);
            return true;
        }else{
            return false;
        }
    }
}
