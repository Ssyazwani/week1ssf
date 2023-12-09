package sg.com.practice.ssf.workshop13.controller;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.com.practice.ssf.workshop13.model.Contact;
import sg.com.practice.ssf.workshop13.model.Idgenerator;
import sg.com.practice.ssf.workshop13.repo.ContactRepo;

@Controller
@RequestMapping("/contact")
public class Contactcontroller {

    @Autowired
    private ContactRepo contactRepo;

    private final String dataDirectory = "C://data1";


    @GetMapping("/addContact")
    public String addContact(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact); 
        return "addContact";
    }

    @PostMapping("/addContact")
public String saveContact(
        @Valid @ModelAttribute("contact") Contact contact,
        BindingResult result,
        Model model,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth) throws IOException {

    LocalDate currTime = LocalDate.now();
    int age = Period.between(dateOfBirth, currTime).getYears();

    if (age < 10 && age > 100) {
        FieldError err = new FieldError("contact", "dateOfBirth", "Cannot be young or old");
        result.addError(err);
        return "addContact";
    }

    if (result.hasErrors()) {
        return "addContact";
    }

    String generatedId = Idgenerator.generateUniqueId();
    contact.setId(generatedId);

    Boolean saveResult = contactRepo.saveContact(contact);

    model.addAttribute("savedContact", contact);
    model.addAttribute("generatedId", generatedId);

    System.out.println(saveResult);

    return "addContact";
}

    @GetMapping("/contacts")
    public String getContactList(Model model) {
    contactRepo.loadContactsFromFile();
    List<Contact> contacts = contactRepo.findAll();
    model.addAttribute("contacts", contacts);
    System.out.println(contacts);
    System.out.println("hi");
    return "contacts"; 
}


    @GetMapping("/contact/{id}")
    public String getContactById(@PathVariable String id, Model model) {
        System.out.println("help");
       
        String filePath = Paths.get(dataDirectory, id + ".txt").toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("<br>");
            }

            model.addAttribute("fileContent", content.toString());
        } catch (FileNotFoundException e) {
        return "error";
    } catch (IOException e) {
        
    }
        return "contactDetails";
    }
}

//UPDATE: http://localhost:8080/contact/contact/DLI3waG3

 // public String addContact (@RequestParam String name
     //                          @RequestParam ("email") String myEmail) ---> request single

     // for alot of values
     // public String addContract (@RequestParam MultivaluedMap<String,String)>form)

     //java way
     //public String addContact (@ModelAttribute Register register) if u create a java object
     //( must have getters and setters) setEmail setName

     // for POST: for multivaluemap 
//           public String addContact(
    // @RequestBody MVM <String,String>form) -->  return "result"










    

