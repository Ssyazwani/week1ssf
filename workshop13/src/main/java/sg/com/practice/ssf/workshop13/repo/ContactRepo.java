package sg.com.practice.ssf.workshop13.repo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import sg.com.practice.ssf.workshop13.model.Contact;
import sg.com.practice.ssf.workshop13.model.Idgenerator;

@Repository
public class ContactRepo {

    final String dirPath = "C://data1";
    final String fileName = "contact.txt";

    private List<Contact> contacts;

    public ContactRepo() throws ParseException {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("1965-08-09");
        Contact con = new Contact("Hsien Loong", "leehsienloong@gov.sg", "91234567", dt);
        con.setId(Idgenerator.generateUniqueId());
        contacts.add(con);
    }

    public List<Contact> findAll() {
        return contacts;
    }

    public Boolean save(Contact contact) throws IOException {
        Boolean result = contacts.add(contact);

        Path directoryPath = Paths.get(dirPath);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path filePath = directoryPath.resolve(fileName);
        OutputStream os = new FileOutputStream(fileName,true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(contact.toString());
        pw.flush();
        pw.close();
        return result;
    }

    public Contact findById(String id) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        System.out.println("Contact not found with ID: " + id);
        return null;
    }
    
 }

