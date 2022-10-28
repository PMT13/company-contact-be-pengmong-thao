package net.yorksolutions.contactsspringboot.services;

import net.yorksolutions.contactsspringboot.models.Company;
import net.yorksolutions.contactsspringboot.models.Contact;
import net.yorksolutions.contactsspringboot.repositories.CompanyRepository;
import net.yorksolutions.contactsspringboot.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class CompanyService {

    CompanyRepository repo;
    ContactRepository contactRepo;

    public CompanyService(CompanyRepository repo, ContactRepository contactRepo) {
        this.repo = repo;
        this.contactRepo = contactRepo;
    }

    public ArrayList<Company> getCompanies() {
        ArrayList<Company> companiesList = new ArrayList<>();
        Iterable<Company> found = this.repo.findAll();

        for(Company company : found)
        {
            companiesList.add(company);
        }
        return companiesList;
    }

    public Company getCompanyById(long id){
        return this.repo.findById(id).orElseThrow();
    }
    public void addCompany(Company newCompany) {
        this.repo.save(newCompany);
    }

    public void addContact(long contactId,long companyId){
        Company foundCompany = this.repo.findById(companyId).orElseThrow();
        Contact foundContact = this.contactRepo.findById(contactId).orElseThrow();
        foundCompany.contactList.add(foundContact);
        foundContact.company.add(foundCompany);
        this.repo.save(foundCompany);
    }

    public Set<Contact> getContacts(long id) {
        Company company = this.repo.findById(id).orElseThrow();
        return company.contactList;
    }

    public boolean deleteCompany(long id) {
        boolean exists = this.repo.existsById(id);
        if(exists){
            this.repo.findById(id).orElseThrow().dismissContacts();
            this.repo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public void updateCompany(long id, Company company) {
        company.id = id;
        this.repo.save(company);
    }
}
