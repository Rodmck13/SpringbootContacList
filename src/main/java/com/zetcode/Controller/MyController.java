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
        var contacts = contactRepository.findAll();
        return contacts.toString();
    }

    @RequestMapping(value="/addContact/{name}/{num}",  method = RequestMethod.GET)
    public String addContact(@PathVariable("name") String name, @PathVariable("num") String num)
    {

        contactRepository.save(new Contact(name, num));

        logger.info("# of Contact: {}", contactRepository.count());



        logger.info("------------------------");

        logger.info("All contact sorted by name in descending order");
        var sortedContacts = contactRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
        logger.info("{}", sortedContacts);

        logger.info("------------------------");



        return sortedContacts.toString();
    }


    @RequestMapping(value="/delete/{nameOrNum}",  method = RequestMethod.GET)
    public String Delete(@PathVariable("nameOrNum") String str)
    {

        logger.info("Deleting: "+str);
        contactRepository.deleteByname(str);
        contactRepository.deleteByNumber(str);
        logger.info("------------------------");
        logger.info("# of contacts: {}", contactRepository.count());

        logger.info("All contacts sorted by name in descending order");
        var sortedContacts = contactRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
        logger.info("{}", sortedContacts);

        logger.info("------------------------");
        return sortedContacts.toString();

    }
    @RequestMapping(value="/deleteAll",  method = RequestMethod.GET)
    public void DeleteAll()
    {

        logger.info("Deleting all contacts");
        contactRepository.deleteAllInBatch();
        logger.info("# of contacts: {}", contactRepository.count());

    }



    @RequestMapping(value="/modifyByNamede/{newName}/{oldName}", method = RequestMethod.GET)
    public String modifyContactByName(@PathVariable("newName") String newName, @PathVariable("oldName") String oldName)
    {

        contactRepository.modifyName(newName, oldName);

        var sortedContacts = contactRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
        logger.info("{}", sortedContacts);

        logger.info("------------------------");
        return sortedContacts.toString();
    }

    @RequestMapping(value="/modifyByNumber/{newNumber}/{oldNumber}", method = RequestMethod.GET)
    public String modifyContactByNumber(@PathVariable("newNumber") String newNumber, @PathVariable("oldNumber") String oldNumber)
    {

        contactRepository.modifyNumber(newNumber, oldNumber);

        var sortedContacts = contactRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
        logger.info("{}", sortedContacts);

        logger.info("------------------------");
        return sortedContacts.toString();
    }




}