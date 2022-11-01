package net.yorksolutions.contactsspringboot.controllers;

import net.yorksolutions.contactsspringboot.models.Company;
import net.yorksolutions.contactsspringboot.models.Contact;
import net.yorksolutions.contactsspringboot.services.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ArrayList<Company> getCompanies(){
        return this.service.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable long id){
        return this.service.getCompanyById(id);
    }

    @GetMapping("/contactList/{id}")
    public Set<Contact> getCompanyContacts(@PathVariable long id){
        return this.service.getContacts(id);
    }

    @PostMapping("/add")
    public void addCompany(@RequestBody Company newCompany){
        this.service.addCompany(newCompany);
    }

    @PostMapping("/addContact")
    public void addContact(@RequestParam long contactId,@RequestParam long companyId){
        this.service.addContact(contactId,companyId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable long id){
        this.service.deleteCompany(id);
    }

    @PutMapping("/update/{id}")
    public void updateCompany(@PathVariable long id, @RequestBody Company company){
        this.service.updateCompany(id,company);
    }
}
