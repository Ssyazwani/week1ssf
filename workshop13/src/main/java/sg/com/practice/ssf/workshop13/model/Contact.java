package sg.com.practice.ssf.workshop13.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @NotEmpty (message = "mandatory")
    @Size(min=3 , max = 64 , message = "First Name must be in between 3 to 64 characters")
    private String name;

    @Email(message= "Invalid email format yo yo")
    @NotBlank(message="Oi, don't leave blank") //if u leave blank, it is an optional field
    private String email;

    //@Pattern(regexp ="\\(8|9)[0-9]{7}", message ="Invalid phone number entered")
    @Pattern(regexp = "\\d{8}", message =" please type 8 numbers")
    private String phoneNumber;

    //(min = 10, max = 100)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message ="Birth date must be past date")
    private Date dateOfBirth;

    private String id;

    public Contact(String name, String email, String phoneNumber, Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.id = Idgenerator.generateUniqueId();
    }

    public static Contact fromString(String line) {
        return null;
    }   

    public boolean Agelimit( LocalDate dateOfBirth , LocalDate currTime  ) { // put in controller
        int age = Period.between(currTime, dateOfBirth).getYears();
        if (age> 10 && age <100);
        return true;
    }

    

    

   

    
    
   
}

 
