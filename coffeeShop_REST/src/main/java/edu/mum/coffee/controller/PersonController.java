package edu.mum.coffee.controller;

import com.google.common.base.Preconditions;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person add(@RequestBody Person person){
        Preconditions.checkNotNull(person);
        return personService.savePerson(person);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Person> getAllPersons(){
        return personService.getAllPerson();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable long id){
        return personService.findById(id);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@PathVariable long id, @RequestBody Person person){
        Preconditions.checkNotNull(person);
        Preconditions.checkNotNull(personService.findById(id));
        if(id == person.getId()) {
            personService.savePerson(person);
        }
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable long id){
        Person person = personService.findById(id);
        Preconditions.checkNotNull(person);
        personService.removePerson(person);
    }
}
