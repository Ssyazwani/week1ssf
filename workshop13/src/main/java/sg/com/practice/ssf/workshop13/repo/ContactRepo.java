package sg.com.practice.ssf.workshop13.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import sg.com.practice.ssf.workshop13.model.Contact;

@Repository
public class ContactRepo {

    final String dirpath= "C://data1";

    final String filename = "contact.txt";

    private List<Contact> contacts;


    public ContactRepo(){
        if (contacts == null) {
            contacts = new ArrayList<Contact>();
        }

        
    }
    
}
