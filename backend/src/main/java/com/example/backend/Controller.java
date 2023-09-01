package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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


    //private final UserID userID;


/*
    @GetMapping("/place-id-Work/{adresse}/{street/{number}")
    public String getPlace_IDA(@PathVariable String adresse, @PathVariable String street, @PathVariable int number, @PathVariable String schluessel) {
        return useranfragen.getPlace_idA();
    }




    @GetMapping("/placeid/{stadt}/{strasse}/{nummer}")
    public PlaceIdResponse PlaceId(@PathVariable String stadt,@PathVariable String strasse,@PathVariable String nummer) {
    PlaceIdResponse response = service.Place_Id(stadt, strasse, nummer);

    userRepo.save(userRepo.findMongoUserByUsername(username));

    return response;
  }
 */

    //Test
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

    // nicht anfassen////////////////////////////////
    /*
    @GetMapping("/get-user/{username}")
    public ResponseEntity<Optional<MongoUser>> findbyusername(@PathVariable String username) {
        Optional<MongoUser> user =  detailService.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

     */
// nicht anfassen ////////////////////////////////
    private static final Logger logger = LoggerFactory.getLogger(MongoUserController.class);


    @GetMapping("/get-user/{username}")
    public ResponseEntity<PlaceIdResponse> findbyusername(@PathVariable String username) {
        Optional<MongoUser> user = detailService.findByUsername(username);
           detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser =user.get();

            //Adresse filtern

            //userRepo.delete(mongoUser.withUsername(username));
            //Adresse an google senden // das geht
            PlaceIdResponse response = service.Place_Id(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer);


            userRepo.save(mongoUser.withPlace_id(String.valueOf(response.getPlaceId())));





        return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    private AdresseUser extractFilteredData(MongoUser user) {
        // Hier kannst du die Filterlogik implementieren, um die gewünschten Informationen aus dem MongoUser-Objekt zu extrahieren
        String filteredInfo1 = user.arbeitsadressestadt; // Beispiel: Annahme, dass es eine Methode getSomeInfo() gibt
        String filteredInfo2 = user.arbeitsadressestrasse; // Beispiel: Annahme, dass es eine Methode getAnotherInfo() gibt
        String filteredInfo3 = user.arbeitsadressenummer;
        // Erstelle ein FilteredUserData-Objekt und gib die extrahierten Daten zurück
        return new AdresseUser(filteredInfo1, filteredInfo2, filteredInfo3);
    }











}





















/*
    @GetMapping("/placeid/{adresse}/{street}/{number}")
    public PlaceIdResponse PlaceId(@PathVariable String adresse,@PathVariable String street,@PathVariable String number) {
        PlaceIdResponse response = service.Place_Id(adresse, street, number);

        return response;


    }


    @GetMapping("/{place_idH}/{place_idW}")
    public PlaceIdResponse Route(@PathVariable String place_idH,@PathVariable String place_idW) {

        return service.timeWithoutTraffic(place_idH,place_idW);

    }

*/



    /*

    @GetMapping("/place-id/{place_idA}/{place-idb}")
    public Useranfragen calculateRoutwithoutTraffic(@PathVariable String place_idA, @PathVariable String place_idB) {
        return service.timeWithoutTraffic(useranfragen.getPlace_idA(), useranfragen.getPlace_idA());

    }

    @GetMapping("/place-id/{place_idA}/{place-idb}")
    public Useranfragen calculateRoutwitTraffic(@PathVariable String place_idA, @PathVariable String place_idB) {
        return service.timeWithTraffic(useranfragen.getPlace_idA(), useranfragen.getPlace_idA());

    }

*/






/*
    @GetMapping("/place-id-Home/{adresse}/{street/{number}")
    public List<Useranfragen> getPlace_IDA(@PathVariable String adresse, @PathVariable String street, @PathVariable int number, @PathVariable String schluessel) {
        return Service. ();


    }

*/


