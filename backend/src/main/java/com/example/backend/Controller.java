package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

@CrossOrigin
@RequestMapping()
public class Controller {

    private final Service service;
    //private final UserRepository userRepository;
    private final Useranfragen useranfragen;
/*
    @GetMapping("/place-id-Work/{adresse}/{street/{number}")
    public String getPlace_IDA(@PathVariable String adresse, @PathVariable String street, @PathVariable int number, @PathVariable String schluessel) {
        return useranfragen.getPlace_idA();
    }


 */

    @GetMapping("/place-id/{adresse}/{street}/{number}")
    public String getPlace_ID(@PathVariable String adresse, @PathVariable String street, @PathVariable int number) {
        return service.getDataHomeAddressGeocode(useranfragen.getAdresse(),useranfragen.getStreet(), useranfragen.getNumber());

    }

    @GetMapping("/place-id/{place_idA}/{place-idb}")
    public Useranfragen calculateRoutwithoutTraffic(@PathVariable String place_idA, @PathVariable String place_idB) {
        return service.timeWithoutTraffic(useranfragen.getPlace_idA(), useranfragen.getPlace_idA());

    }

    @GetMapping("/place-id/{place_idA}/{place-idb}")
    public Useranfragen calculateRoutwitTraffic(@PathVariable String place_idA, @PathVariable String place_idB) {
        return service.timeWithTraffic(useranfragen.getPlace_idA(), useranfragen.getPlace_idA());

    }








/*
    @GetMapping("/place-id-Home/{adresse}/{street/{number}")
    public List<Useranfragen> getPlace_IDA(@PathVariable String adresse, @PathVariable String street, @PathVariable int number, @PathVariable String schluessel) {
        return Service. ();


    }

*/
}

