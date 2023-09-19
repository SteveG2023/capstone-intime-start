package com.example.backend;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("/api/user")



public class Controller {

    @Autowired
    private MongoUserRepo userRepo;


    private final Service service;

// User Location

    // wird von RegisterWork verwendet




    @GetMapping("/placeidwork/{username}/{arbeitsadressestadt}/{arbeitsadressestrasse}/{arbeitsadressenummer}")
    public ResponseEntity<PlaceIdResponse> changeAdressWork(@PathVariable String username, @PathVariable String arbeitsadressestadt,@PathVariable String arbeitsadressestrasse,@PathVariable String arbeitsadressenummer) {
       return service.placeidAbfragenWork(username,arbeitsadressestadt,arbeitsadressestrasse,arbeitsadressenummer);


    }
// Die Function wird genutzt in der HomeAdressPage

    @GetMapping("/placeidhome/{username}/{wohnadressestadt}/{wohnadressestrasse}/{wohnadressenummer}")
    public ResponseEntity<PlaceIdResponse> changeAdressHome(@PathVariable String username, @PathVariable String wohnadressestadt,@PathVariable String wohnadressestrasse,@PathVariable String wohnadressenummer) {
      return service.placeidAbfragenHome(username,wohnadressestadt,wohnadressestrasse,wohnadressenummer);


    }


// User Time


    @PostMapping("/time/{username}/{startZeit}/{endZeit}/{vorbereitungsZeit}/{maximalweckZeit}")
    public ResponseEntity<MongoUser> saveTime(@PathVariable String username, @PathVariable String startZeit, @PathVariable String endZeit, @PathVariable String vorbereitungsZeit, @PathVariable String maximalweckZeit ) throws
            InstantiationException, IllegalAccessException, InterruptedException {

       return service.testzeit(username,startZeit,endZeit,vorbereitungsZeit,maximalweckZeit);
    }


    // wird vom Wecker verwendet
    @GetMapping("/weckzeit/{username}")
    public ResponseEntity<Integer> Weckzeit(@PathVariable String username) throws InstantiationException, IllegalAccessException, InterruptedException {


     return service.weckzeitTime(username);
    }


    //Teure Variante
    //wird noch nicht verwendet
    @GetMapping("/traffictime/{username}")
    public ResponseEntity<ResponseDuration> findDurationTimeTraffic(@PathVariable String username) throws InterruptedException {
      return service.trafficTime(username);
    }


    // Kostengünstige Variante
    // hier wird nur zusätzliche Zeit zurückgegeben die der user mehr benötigt
    @GetMapping("/duration/{username}")
    public ResponseEntity<Integer> findDuration(@PathVariable String username) throws
            InstantiationException, IllegalAccessException {

        return service.durationMinute(username);


    }
    // diese Funktion funktioniert
    @GetMapping ("/anfragen2/{username}")
        public ResponseEntity<ResponseDuration> speicherungDerUserDatenLocation(@PathVariable String username) throws InterruptedException {

        return   service.anfragen(username);

    }

    @GetMapping("/me2")
    public String getMe2() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

    }


    @PostMapping("/logout")
    public String login(HttpSession httpSession) {
        httpSession.invalidate();
        SecurityContextHolder.clearContext();
        return "logged out";

    }
    @PostMapping("/delete/{username}")
    public String delete(String username) {
    service.deleteUser(username);
        return "delete";

    }


    @PostMapping("/login")
    public String login() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @PostMapping("/register")
    public String saveUser(@RequestBody MongoUser user) {


        return service.saveUser(user).getUsername();
    }

    @GetMapping("/me")
    public String getMe1(Principal principal) {
        if (principal != null) {
            return principal.getName();

        }
        return "unbekannt";

    }

/////////////////////////////////////////////TestsEndpunkte

    @GetMapping("/durationTest/{username}")
    public ResponseEntity<Integer> findDurationTest(@PathVariable String username) throws
            InstantiationException, IllegalAccessException {

        Optional<MongoUser> user = service.findByUsername(username);
        //  detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            int sum = 15;
            System.out.print(sum);
            return ResponseEntity.ok(sum);

        } else {
            return ResponseEntity.notFound().build();
        }

    }



    @GetMapping("/durationNonTrafficTest/{username}")
    public ResponseEntity<Integer> findDurationnoTraffic(@PathVariable String username) throws InstantiationException, IllegalAccessException {
        Optional<MongoUser> user = service.findByUsername(username);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            int responseEntity = (mongoUser.duration);
            int sum = ((responseEntity * 10) / 3600);


            System.out.println(sum);
            return ResponseEntity.ok(sum);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/weckzeittest/{username}")
    public int weckzeitTest(@PathVariable String username) {

        int sum = 800;
        System.out.print(sum);

        return sum;
    }




}



















