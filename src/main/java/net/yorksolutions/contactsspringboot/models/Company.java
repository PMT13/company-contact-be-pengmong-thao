package net.yorksolutions.contactsspringboot.models;
import net.yorksolutions.contactsspringboot.models.Contact;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public Long id;

    public String name;
    public String location;
    public int employees;

    @ManyToMany
    public Set<Contact> contactList;       // Set is like a list except it doesn't accept duplicates

    public void dismissContact(Contact contact) {
        this.contactList.remove(contact);
    }

    public void dismissContacts(){
        this.contactList.forEach(contact -> contact.company = null);
        this.contactList.clear();
    }
}
