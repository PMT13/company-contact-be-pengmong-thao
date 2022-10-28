package net.yorksolutions.contactsspringboot.repositories;

import net.yorksolutions.contactsspringboot.models.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    public Optional<Contact> findByFirstName(String firstName);
}
