package sg.com.practice.ssf.workshop13;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop13Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Workshop13Application.class, args);
	}
	
	
	@Override
    public void run(String... args) throws Exception {
		if (args.length > 0) {
            String dataDir = args[0];

            File fileDir = new File(dataDir);

            if (!fileDir.exists()) {
                fileDir.mkdir();
                System.out.println("***" + fileDir.getAbsolutePath());
                System.out.println("***" + fileDir.getPath());
            } else {
                System.out.println(fileDir.getAbsolutePath());
            }
        }
    }

	}

   


