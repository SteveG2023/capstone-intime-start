package com.example.backend;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("/api/user")



public class Controller {

    @Autowired
    private MongoUserRepo userRepo;
    private MongoUser user;

    private final Service service;

    @GetMapping("/placeidWork/{arbeitsadressestadt}/{arbeitsadressestrasse}/{arbeitsadressenummer}")
    public PlaceIdResponse PlaceIda(@PathVariable String arbeitsadressestadt, @PathVariable String arbeitsadressestrasse, @PathVariable String arbeitsadressenummer) {
        PlaceIdResponse response = service.Place_Id(arbeitsadressestadt, arbeitsadressestrasse, arbeitsadressenummer);
        System.out.println(response);


        return response;
    }

    @GetMapping("/placeidHome/{stadt}/{strasse}/{nummer}")
    public PlaceIdResponse PlaceIdw(@PathVariable String wohnadressestadt, @PathVariable String wohnadressestrasse, @PathVariable String wohnadressenummer) {
        PlaceIdResponse response = service.Place_Id(wohnadressestadt, wohnadressestrasse, wohnadressenummer);
        // Algo zum durchsuchen der DB
        user.setPlace_idWork(response.toString());


        return response;
    }




    @GetMapping("/placeidw/{username}")
    public ResponseEntity<PlaceIdResponse> findbyusernamew(@PathVariable String username) {
        Optional<MongoUser> user = service.findByUsername(username);
        service.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();

            PlaceIdResponse response = service.Place_Id(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer);
            userRepo.save(mongoUser.withPlace_idWork(String.valueOf(response.getPlaceId())));

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/placeidh/{username}")
    public ResponseEntity<PlaceIdResponse> findbyusername(@PathVariable String username) {
        Optional<MongoUser> user = service.findByUsername(username);
        service.deleteUser(user.orElseThrow().id);
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







        @GetMapping("/weckerzeit/{username}")
        public ResponseEntity<String> findWeckerzeit(@PathVariable String username) {
            Optional<MongoUser> user = service.findByUsername(username);
            if (user.isPresent()) {
                MongoUser mongoUser = user.get();
                String weckerzeit = mongoUser.getStartZeit();
                System.out.println(weckerzeit);
                return ResponseEntity.ok(weckerzeit);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @GetMapping("/vorbereitungszeit/{username}")
    public ResponseEntity<String> findVorbereitungsZeit(@PathVariable String username) {
        Optional<MongoUser> user = service.findByUsername(username);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            String weckerzeit = mongoUser.getVorbereitungsZeit();
            System.out.println(weckerzeit);
            return ResponseEntity.ok(weckerzeit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/endzeit/{username}")
    public ResponseEntity<String> findEndZeit(@PathVariable String username) {
        Optional<MongoUser> user = service.findByUsername(username);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            String weckerzeit = mongoUser.getEndZeit();
            System.out.println(weckerzeit);
            return ResponseEntity.ok(weckerzeit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }







//Teure Variante
        @GetMapping("/traffictime/{username}")
        public ResponseEntity<ResponseDuration> findDurationTimeTraffic (@PathVariable String username) throws InterruptedException {
            Optional<MongoUser> user = service.findByUsername(username);

            if (user.isPresent()) {
                MongoUser mongoUser = user.get();
                 //hole die Zeit  mit Verkehr
                ResponseDuration inhalt = service.duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);


                if (inhalt != null) {
                    int timewithtraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
                    int timewhitouttraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getValue();

                    System.out.println("Zeit mit Verkehr: " + timewithtraffic);

                    int summe = timewithtraffic - timewhitouttraffic;

                    System.out.println("Zeit ohne Verkehr: " + timewhitouttraffic);
                    System.out.println("Differenz: " + summe/60+" Minuten");
                    userRepo.save(mongoUser.withDuration(Integer.parseInt(String.valueOf(timewhitouttraffic))));

                    if (timewithtraffic > 3) {
                        int delayInSeconds = 3;
                        System.out.println("Die Anfrage dauert länger. Verzögerung von " + delayInSeconds + " Sekunden.");
                        Thread.sleep(delayInSeconds * 1000);
                    }

                    System.out.println("Fortsetzung des Codes nach der Verzögerung");
                } else {
                    System.out.println("Fehler bei der Anfrage");
                }



                return ResponseEntity.ok(inhalt);
            } else {
                return ResponseEntity.notFound().build();
            }
        }



// Kostengünstige Variante
        @GetMapping("/duration/{username}")
        public ResponseEntity<Integer> findDuration (@PathVariable String username) throws
        InstantiationException, IllegalAccessException {

            Optional<MongoUser> user = service.findByUsername(username);
            //  detailService.deleteUser(user.orElseThrow().id);
            if (user.isPresent()) {
                MongoUser mongoUser = user.get();

                ResponseDuration inhalt = service.duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);
                System.out.println(inhalt);
                int sum ;
                int traffic=inhalt.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
                int notraffic =inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getValue();
                System.out.println(traffic);
                System.out.println(notraffic);

                sum=(traffic-notraffic)/60;
                System.out.println(sum);


                return ResponseEntity.ok(sum);

            } else {
                return ResponseEntity.notFound().build();
            }

        }
    @PostMapping("/time/{username}/{startZeit}/{endZeit}/{vorbereitungsZeit}")
                 public ResponseEntity<MongoUser> saveTime (@PathVariable String username,@PathVariable String startZeit,@PathVariable String endZeit,@PathVariable String vorbereitungsZeit) throws
            InstantiationException, IllegalAccessException {

                Optional<MongoUser> user = service.findByUsername(username);
        //  detailService.deleteUser(user.orElseThrow().id);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            userRepo.save(mongoUser.withEndZeit(endZeit).withStartZeit(startZeit).withVorbereitungsZeit(vorbereitungsZeit));

            return ResponseEntity.ok(mongoUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/anfragen/{username}")
    public ResponseEntity<ResponseDuration> Test (@PathVariable String username) throws InterruptedException {

        Optional<MongoUser> user = service.findByUsername(username);
//suche den User
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            System.out.println(username);

            PlaceIdResponse placeipHome = service.Place_Id(mongoUser.getArbeitsadressestadt(),mongoUser.getArbeitsadressestrasse(),mongoUser.getArbeitsadressenummer());

            if (placeipHome != null) {

                userRepo.save(mongoUser.withPlace_idHome(placeipHome.getPlaceId()).withPlace_idWork(placeipHome.getPlaceId()));
                Thread.sleep(3000);
                System.out.println(placeipHome);
            }

            PlaceIdResponse placeipWork = service.Place_Id(mongoUser.getWohnadressestadt(), mongoUser.getWohnadressestrasse(), mongoUser.getWohnadressenummer());

            if (placeipWork != null) {

                Thread.sleep(3000);
                System.out.println(placeipWork);
            }
            ResponseDuration inhalt = service.duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);

            if (inhalt != null) {
                int timewithtraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
                int timewhitouttraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getValue();

                System.out.println("Zeit mit Verkehr: " + timewithtraffic);

                int summe = timewithtraffic - timewhitouttraffic;

                System.out.println("Zeit ohne Verkehr: " + timewhitouttraffic);
                System.out.println("Differenz: " + summe/60+" Minuten");
                userRepo.save(mongoUser.withPlace_idHome(placeipHome.getPlaceId()).withPlace_idWork(placeipHome.getPlaceId()).withDuration(Integer.parseInt(String.valueOf(timewhitouttraffic))));

                if (timewithtraffic > 3) {
                    int delayInSeconds = 3;
                    System.out.println("Die Anfrage dauert länger. Verzögerung von " + delayInSeconds + " Sekunden.");
                    Thread.sleep(delayInSeconds * 1000);
                }

                System.out.println("Fortsetzung des Codes nach der Verzögerung");
            } else {
                System.out.println("Fehler bei der Anfrage");
            }
              return ResponseEntity.ok(inhalt);
        } else {
            return ResponseEntity.notFound().build();



        }

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


    @PostMapping("/login")
    public String login()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/register")
    public String saveUser(@RequestBody MongoUser user) {


        return service.saveUser(user).getUsername();
    }
    @GetMapping("/me")
    public String getMe1(Principal principal){
        if(principal !=null){
            return principal.getName();

        }
        return "unbekannt";

    }


}































