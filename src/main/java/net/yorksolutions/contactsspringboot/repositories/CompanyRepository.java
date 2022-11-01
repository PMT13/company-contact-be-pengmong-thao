package net.yorksolutions.contactsspringboot.repositories;

import net.yorksolutions.contactsspringboot.models.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    public Company findByName(String name);
}
