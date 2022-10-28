package net.yorksolutions.contactsspringboot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import javax.persistence.*;
import java.util.Set;

@Entity  // tells JPA to create a table called contact and have columns that match up with the contact class
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;

    public String firstName;
    public String lastName;
    public String displayName;
    public String email;

    @ManyToMany
    public Set<Company> company;

    public Set<Company> getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company.add(company);
    }

    @Autowired
    public Contact(long id, String firstName, String lastName, String displayName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.email = email;
    }

    public Contact() {
    }

    public void dismissCompany(){
        this.company.forEach(company -> company.dismissContact(this));
    }
}
