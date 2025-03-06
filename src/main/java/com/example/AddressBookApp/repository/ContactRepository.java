package com.example.AddressBookApp.repository;

import com.example.AddressBookApp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {}