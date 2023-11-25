package sg.com.practice.ssf.workshop13.controller;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.com.practice.ssf.workshop13.model.Contact;
import sg.com.practice.ssf.workshop13.model.Idgenerator;
import sg.com.practice.ssf.workshop13.repo.ContactRepo;

@Controller
@RequestMapping("/contact")
public class Contactcontroller {

    @Autowired
    ContactRepo Repo;

    @GetMapping("/addContact")
    public String addContact(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "addContact";
    }

    @PostMapping("/addContact")
    public String saveContact(
            @Valid @ModelAttribute("contact") Contact con,
            BindingResult result,
            Model model) throws IOException {

        if (result.hasErrors()) {
            return "addContact";
        }

        String id = Idgenerator.generateUniqueId();
        con.setId(id);

    
        Boolean resultResult = Repo.save(con);

        model.addAttribute("savedContact", con);
        model.addAttribute("generatedId", id);

        return "redirect:/contact/list";
    }

    @GetMapping("contact/<id>")
    public String getContact(@PathVariable("id")String id, Model model) {
        
        Contact contact = Repo.findById(id);
        model.addAttribute("contact", contact);
            
        return "contactDetails";
        
    }
}




    

