package com.Brainworks.CitizenAppEntity.ServiceImpl;

import com.Brainworks.CitizenAppEntity.binding.CitizenAppBinding;
import com.Brainworks.CitizenAppEntity.entity.Citizen_Apps;
import com.Brainworks.CitizenAppEntity.repo.CitizenAppRepo;
import com.Brainworks.CitizenAppEntity.service.CitizenAppService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CitizenAppImpl implements CitizenAppService {

    @Autowired
    private CitizenAppRepo citizenAppRepo;

    @Override
    public Integer createCitizen(CitizenAppBinding citizen_apps) {

//        String endurl="";
//        RestTemplate tr=new RestTemplate();
//        ResponseEntity<Citizen_Apps> forEntity = tr.getForEntity(endurl, Citizen_Apps.class, citizen_apps.getSsn());
//        Citizen_Apps body = forEntity.getBody();


//        WebClient webClient = WebClient.create();
//        String block = webClient.get()// get Request
//                .uri(endurl, citizen_apps.getSsn()) // Url
//                .retrieve() // Retrieve data
//                .bodyToMono(String.class)  //Specify Response Type
//                .block();   // To make synchronous call
        if("New Jersey".equals(citizen_apps.getStateName()))
        {
            Citizen_Apps entity=new Citizen_Apps();
            BeanUtils.copyProperties(citizen_apps,entity);
            Citizen_Apps save = citizenAppRepo.save(entity);
            return save.getApp_Id();
        }
        return 0 ;
    }
}
