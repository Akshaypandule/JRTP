package com.BrainWorks.WelcomeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeController {

    @Autowired
    private GreetClient greetClient;

    @GetMapping("welcome")
    public WelcomeResponse WelcomeAPi(){

        String msg="Welcome to BrainWorks";
        String greet = greetClient.GreetApi();

        RestTemplate restTemplate = new RestTemplate();
        String endPoint="http://petstore.execute-api.eu-north-1.amazonaws.com/petstore/pets/1";

        ResponseEntity<Pet> forEntity = restTemplate.getForEntity(endPoint, Pet.class);
        Pet body = forEntity.getBody();

        WelcomeResponse finalResponse=new WelcomeResponse();
        finalResponse.setWelcomeMsg(msg);
        finalResponse.setGreetMsg(greet);
        finalResponse.setPets(body);

        return finalResponse;
    }
}
