package edu.mum.coffee.controller;

import com.google.common.base.Preconditions;
import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.model.PersonDTO;
import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPersons(Model model) {
        model.addAttribute("persons", personService.getAllPerson());
        return "persons";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getAddNewPersonForm(Model model) {
        PersonDTO newPerson = new PersonDTO();
        model.addAttribute("newPerson", newPerson);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processAddNewPersonForm(@ModelAttribute("newPerson") @Valid PersonDTO newPerson, BindingResult result) {

        if (result.hasErrors()) {
            return "signup";
        }
        Address address = new Address();
        address.setCity(newPerson.getCity());
        address.setCountry(newPerson.getCountry());
        address.setState(newPerson.getState());
        address.setZipcode(newPerson.getZipcode());

        Person person = new Person();
        person.setFirstName(newPerson.getFirstName());
        person.setLastName(newPerson.getLastName());
        person.setEmail(newPerson.getEmail());
        person.setPassword(newPerson.getPassword());
        person.setRole(newPerson.getRole());
        person.setAddress(address);
        person.setPhone(newPerson.getPhone());
        person.setEnable(newPerson.isEnable());

        personService.savePerson(person);
        return "redirect:/persons";
    }

    @RequestMapping("/personDetail")
    public String getPersonById(@RequestParam("id") long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "person";
    }

    @GetMapping(value = "/update/{id}")
    public String getUpdatePersonForm(@PathVariable long id, Model model) {
        Person person = personService.findById(id);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setEmail(person.getEmail());
        personDTO.setPassword(person.getPassword());
        personDTO.setRole(person.getRole());
        personDTO.setCity(person.getAddress().getCity());
        personDTO.setCountry(person.getAddress().getCountry());
        personDTO.setState(person.getAddress().getState());
        personDTO.setZipcode(person.getAddress().getZipcode());
        personDTO.setPhone(person.getPhone());
        personDTO.setEnable(person.isEnable());

        model.addAttribute("personDTO", personDTO);
        return "updatePerson";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updatePerson(@PathVariable long id, @ModelAttribute("personDTO") @Valid PersonDTO personDTO, BindingResult result) {
        Preconditions.checkNotNull(personDTO);
        Person updatedPerson = personService.findById(id);
        Address updatedAddress = updatedPerson.getAddress();

        updatedAddress.setCity(personDTO.getCity());
        updatedAddress.setCountry(personDTO.getCountry());
        updatedAddress.setState(personDTO.getState());
        updatedAddress.setZipcode(personDTO.getZipcode());

        updatedPerson.setFirstName(personDTO.getFirstName());
        updatedPerson.setLastName(personDTO.getLastName());
        updatedPerson.setEmail(personDTO.getEmail());
        updatedPerson.setPassword(personDTO.getPassword());
        updatedPerson.setRole(personDTO.getRole());
        updatedPerson.setAddress(updatedAddress);
        updatedPerson.setPhone(personDTO.getPhone());
        updatedPerson.setEnable(personDTO.isEnable());

        personService.savePerson(updatedPerson);
        return "redirect:/persons";
    }

    @GetMapping(value = "/updateProfile")
    public String getUpdateProfileForm(@RequestParam String email, Model model) {
        Person person = personService.findByEmail(email);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setEmail(person.getEmail());
        personDTO.setPassword(person.getPassword());
        personDTO.setRole(person.getRole());
        personDTO.setCity(person.getAddress().getCity());
        personDTO.setCountry(person.getAddress().getCountry());
        personDTO.setState(person.getAddress().getState());
        personDTO.setZipcode(person.getAddress().getZipcode());
        personDTO.setPhone(person.getPhone());
        personDTO.setEnable(person.isEnable());

        model.addAttribute("personDTO", personDTO);
        return "updatePerson";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@RequestParam String email, @ModelAttribute("personDTO") @Valid PersonDTO personDTO, BindingResult result) {
        Preconditions.checkNotNull(personDTO);
        Person updatedPerson = personService.findByEmail(email);
        Address updatedAddress = updatedPerson.getAddress();

        updatedAddress.setCity(personDTO.getCity());
        updatedAddress.setCountry(personDTO.getCountry());
        updatedAddress.setState(personDTO.getState());
        updatedAddress.setZipcode(personDTO.getZipcode());

        updatedPerson.setFirstName(personDTO.getFirstName());
        updatedPerson.setLastName(personDTO.getLastName());
        updatedPerson.setEmail(personDTO.getEmail());
        updatedPerson.setPassword(personDTO.getPassword());
        updatedPerson.setRole(personDTO.getRole());
        updatedPerson.setAddress(updatedAddress);
        updatedPerson.setPhone(personDTO.getPhone());
        updatedPerson.setEnable(personDTO.isEnable());

        personService.savePerson(updatedPerson);
        return "redirect:/products";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deletePerson(@PathVariable long id) {
        System.out.println("IN delete Person");
        Person person = personService.findById(id);
        Preconditions.checkNotNull(person);
        personService.removePerson(person);
        return "redirect:/persons";
    }
}
