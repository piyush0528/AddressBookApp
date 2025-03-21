package com.example.AddressBookApp.controller;
import com.example.AddressBookApp.dto.ContactDTO;
import com.example.AddressBookApp.service.ContactService;
import com.example.AddressBookApp.model.Contact;
import com.example.AddressBookApp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    // Constructor-based Dependency Injection
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {
        Optional<ContactDTO> contactDTO = contactService.getContactById(id);
        return contactDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactDTO> addContact(@RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contactService.addContact(contactDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Optional<ContactDTO> updatedContact = contactService.updateContact(id, contactDTO);
        return updatedContact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        boolean isDeleted = contactService.deleteContact(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}