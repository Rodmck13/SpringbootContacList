package com.zetcode;

import com.zetcode.model.Contact;
import com.zetcode.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void run(String... args) throws Exception {
/*
        contactRepository.save(new Contact("Bratislava", "234334"));
        contactRepository.save(new Contact("Budapest", "234334"));
        contactRepository.save(new Contact("Prague", "234334"));
*/

    }
}