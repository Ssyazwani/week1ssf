package sg.com.practice.ssf.workshop13.repo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.validation.Valid;
import sg.com.practice.ssf.workshop13.model.Contact;
import sg.com.practice.ssf.workshop13.model.Idgenerator;

@Repository
public class ContactRepo {

    final String dirPath = "C://data1";

    public List<Contact> contacts;

    public ContactRepo() throws ParseException {
        contacts = new ArrayList<>();
        loadContactsFromFile();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }


    private String getContactFilePath(String id) {
        return Paths.get(dirPath, id + ".txt").toString();

    }

    public void loadContactsFromFile() {
        try {
            Files.createDirectories(Paths.get(dirPath));
            File dir = new File(dirPath);
    
            File[] files = dir.listFiles();
    
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        String id = fileName.substring(0, fileName.lastIndexOf(".txt"));
                        String filePath = getContactFilePath(id);
    
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                // Skip empty or whitespace-only lines
                                if (!line.trim().isEmpty()) {
                                    Contact contact = Contact.fromString(line);
                                    contacts.add(contact);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        System.out.println("loading method" + contacts);
    }
    

    public List<Contact> findAll() {
        System.out.println(contacts);
        return new ArrayList<>(contacts);
    }
    

    public void printContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public Boolean saveContact(Contact contact) {
        try {
            String filePath = getContactFilePath(contact.getId());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String rec = String.format("%s,%s,%s,%s%n",
                        contact.getName(), contact.getEmail(), contact.getPhoneNumber(),
                        new SimpleDateFormat("yyyy-MM-dd").format(contact.getDateOfBirth()));

                writer.write(rec);
            }
            contacts.add(contact);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contact getContactById(String targetId) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(targetId)) {
                return contact;
            }
        }
        return null;
    }
}

