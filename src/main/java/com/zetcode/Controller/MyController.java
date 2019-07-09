package com.zetcode.Controller;

import com.zetcode.MyRunner;
import com.zetcode.model.Contact;
import com.zetcode.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(value="/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {

        return "This is Home page";
    }

    @RequestMapping(value="/addUser/{id}",  method = RequestMethod.GET)
    public String sayHello(@PathVariable("id") String name)
    {

        contactRepository.save(new Contact(name, "234334"));

        logger.info("# of cities: {}", contactRepository.count());

        logger.info("All cities unsorted:");
        var cities = contactRepository.findAll();
        logger.info("{}", cities);

        logger.info("------------------------");

        logger.info("All cities sorted by name in descending order");
        var sortedCities = contactRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
        logger.info("{}", sortedCities);

        logger.info("------------------------");

        logger.info("Deleting all cities");
        contactRepository.deleteAllInBatch();

        logger.info("# of cities: {}", contactRepository.count());


        return cities.toString();
    }
}