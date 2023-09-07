package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("/api/user")
public class Controller {
    @Autowired
    private MongoUserRepo userRepo;

    @Autowired
    private MongoUserDetailService detailService;
    private MongoUser user;

    private final Service service;
    @Autowired
    private final AdresseUser adresseUser;




    @GetMapping("/placeida/{arbeitsadressestadt}/{arbeitsadressestrasse}/{arbeitsadressenummer}")
    public PlaceIdResponse PlaceIda(@PathVariable String arbeitsadressestadt, @PathVariable String arbeitsadressestrasse, @PathVariable String arbeitsadressenummer) {
        PlaceIdResponse response = service.Place_Id(arbeitsadressestadt, arbeitsadressestrasse, arbeitsadressenummer);


        return response;
    }

    @GetMapping("/placeidw/{username}/{stadt}/{strasse}/{nummer}")
    public PlaceIdResponse PlaceIdw(@PathVariable String wohnadressestadt, @PathVariable String wohnadressestrasse, @PathVariable String wohnadressenummer) {
        PlaceIdResponse response = service.Place_Id(wohnadressestadt, wohnadressestrasse, wohnadressenummer);
        // Algo zum durchsuchen der DB
        user.setPlace_idWork(response.toString());


        return response;
    }



    @GetMapping("/placeidw/{username}")
    public ResponseEntity<PlaceIdResponse> findbyusernamew(@PathVariable String username) {
        Optional<MongoUser> user = detailService.findByUsername(username);
        detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();

            //Adresse filtern

            //userRepo.delete(mongoUser.withUsername(username));
            //Adresse an google senden // das geht
            PlaceIdResponse response = service.Place_Id(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer);


            userRepo.save(mongoUser.withPlace_idWork(String.valueOf(response.getPlaceId())));

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/placeidh/{username}")
    public ResponseEntity<PlaceIdResponse> findbyusername(@PathVariable String username) {
        Optional<MongoUser> user = detailService.findByUsername(username);
        detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();

            //Adresse filtern

            //userRepo.delete(mongoUser.withUsername(username));
            //Adresse an google senden // das geht

            PlaceIdResponse response = service.Place_Id(mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);

            userRepo.save(mongoUser.withPlace_idHome(String.valueOf(response.getPlaceId())));

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/traffic/{username}")
    public ResponseEntity<ResponseDuration> findDurationTraffic(@PathVariable String username) {
        Optional<MongoUser> user = detailService.findByUsername(username);
        //  detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();

            ResponseDuration inhalt = service.duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);


            String durationText = inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getText();
            System.out.println(durationText);

            userRepo.save(mongoUser.withDuration(durationText));


            return ResponseEntity.ok(inhalt);
        } else {
            return ResponseEntity.notFound().build();
        }

    }














    @GetMapping("/duration/{username}")
    public ResponseEntity<ResponseDuration> findDuration(@PathVariable String username) throws InstantiationException, IllegalAccessException {

        Optional<MongoUser> user = detailService.findByUsername(username);
        //  detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();

            ResponseDuration inhalt = service.duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);


            //userRepo.save(mongoUser.withDuration(inhalt[0].Route[0].Leg[0].Duration[0].value[1];


            return ResponseEntity.ok(inhalt);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
































