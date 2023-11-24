package sg.com.practice.ssf.workshop13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.com.practice.ssf.workshop13.model.Contact;

@Controller
@RequestMapping("/contact")
public class Contactcontroller {

    @GetMapping()
    public String addContact (Model model){
        Contact con = new Contact();
        model.addAttribute("contact", con);

        return "addContact";

    }


    
}
