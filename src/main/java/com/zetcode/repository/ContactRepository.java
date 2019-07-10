package com.zetcode.repository;

import com.zetcode.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Modifying
    @Transactional
    void deleteByname(String name);

    @Modifying
    @Transactional
    void deleteByNumber(String number);

    @Modifying
    @Transactional
    @Query("UPDATE Contact  SET name = :newName WHERE name = :oldName")
    void modifyName(String newName, String oldName);

    @Modifying
    @Transactional
    @Query("UPDATE Contact  SET number = :newNumber WHERE number = :oldNumber")
    void modifyNumber(String newNumber, String oldNumber);

}