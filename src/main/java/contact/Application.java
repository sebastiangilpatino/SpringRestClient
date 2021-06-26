package contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private RestOperations restTemplate;
    private String serverUrl = "http://localhost:8080/bank";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // add a accountAccountDTO
        restTemplate.postForLocation(serverUrl, new AccountDTO(667, "Jon Doe"));
        // add a accountAccountDTO
        restTemplate.postForLocation(serverUrl, new AccountDTO(668, "Adam Doe"));

        AccountDTO accountDTO;

        // get Account
        accountDTO = restTemplate.getForObject(serverUrl + "/667", AccountDTO.class);
        System.out.println("--------------------Contact info from 667----------------------");
        System.out.println(accountDTO);

        //deposit
        restTemplate.postForLocation(serverUrl + "?accountNumber=667&operation='deposit'&amount=122.25"
                , null );
        accountDTO = restTemplate.getForObject(serverUrl + "/667", AccountDTO.class);
        System.out.println("--------------------Contact info from 667----------------------");
        System.out.println(accountDTO);

        //withdraw
        restTemplate.postForLocation(serverUrl + "?accountNumber=667&operation='withdraw'&amount=122.25"
                , null );
        accountDTO = restTemplate.getForObject(serverUrl + "/667", AccountDTO.class);
        System.out.println("--------------------Contact info from 667----------------------");
        System.out.println(accountDTO);

        //delete Account
        restTemplate.delete(serverUrl+"/667");
        accountDTO = restTemplate.getForObject(serverUrl + "/667", AccountDTO.class);
        System.out.println("--------------------Contact info from 667----------------------");
        System.out.println(accountDTO);

    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
