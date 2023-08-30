package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("/api/user")
public class Controller {
    @Autowired
    private MongoUserRepo userRepo;

    private final Service service;


    //private final UserID userID;


/*
    @GetMapping("/place-id-Work/{adresse}/{street/{number}")
    public String getPlace_IDA(@PathVariable String adresse, @PathVariable String street, @PathVariable int number, @PathVariable String schluessel) {
        return useranfragen.getPlace_idA();
    }




    @GetMapping("/place-id/{adresse}/{street}/{number}")
    public String getPlace_ID(@PathVariable String adresse, @PathVariable String street, @PathVariable int number) {
        return service.getDataHomeAddressGeocode(useranfragen.getAdresse(),useranfragen.getStreet(), useranfragen.getNumber());

    }


    //Test
    @GetMapping("")
    public PlaceIdResponse PlaceId() {

        return service.placeIdResponseFunction("Magdeburg","Breiterweg","5");

    }
*/


    @GetMapping("/placeid/{adresse}/{street}/{number}")
    public PlaceIdResponse PlaceId(@PathVariable String adresse,@PathVariable String street,@PathVariable String number) {
        PlaceIdResponse response = service.Place_Id(adresse, street, number);

        return service.Place_Id(adresse,street,number);


    }
    @GetMapping("/{place_idH}/{place_idW}")
    public PlaceIdResponse Route(@PathVariable String place_idH,@PathVariable String place_idW) {

        return service.timeWithoutTraffic(place_idH,place_idW);

    }





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
}

