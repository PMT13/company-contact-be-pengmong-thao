package net.yorksolutions.contactsspringboot.controllers;

import net.yorksolutions.contactsspringboot.services.ContactService;
import net.yorksolutions.contactsspringboot.models.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {

    ContactService service;

    public ContactController(ContactService service){
        this.service = service;
    }

    @GetMapping("/firstname/{firstName}")
    public Contact getContactByFirstName(@PathVariable String firstName){
        return this.service.getContactByFirstName(firstName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/id/{id}")
    public Contact getContactById(@PathVariable long id){
        return this.service.getContactById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ArrayList<Contact> getContacts(){
        return this.service.getContacts();
    }

    @PostMapping("/add")
    public void addContact(@RequestBody Contact newContact){
        this.service.addContact(newContact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id){
        boolean deleted = this.service.deleteContact(id);
        if(deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Successful Delete");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("Unsuccessful Delete");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable long id, @RequestBody Contact newContact){
        boolean updated = this.service.updateContact(id,newContact);
        if(updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Updated");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("Unsuccessfully Updated");
        }
    }
}
