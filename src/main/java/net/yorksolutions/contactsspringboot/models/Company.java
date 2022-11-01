package net.yorksolutions.contactsspringboot.models;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Company {
    @Id
    public Long id;

    public String name;
    public String location;
    public int employees;

    // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
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
