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
    private MongoUser user;

    private final Service service;

// User Location

    // wird von RegisterWork verwendet




    @GetMapping("/placeidwork/{username}/{arbeitsadressestadt}/{arbeitsadressestrasse}/{arbeitsadressenummer}")
    public ResponseEntity<PlaceIdResponse> changeAdressWork(@PathVariable String username, @PathVariable String arbeitsadressestadt,@PathVariable String arbeitsadressestrasse,@PathVariable String arbeitsadressenummer) {
        Optional<MongoUser> user = service.findByUsername(username);

        if (user.isPresent()) {
            MongoUser mongoUser = user.get();


            PlaceIdResponse response = service.Place_Id(mongoUser.arbeitsadressenummer, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressestadt);
            String place = response.getPlaceId();

            userRepo.save(mongoUser.withArbeitsadressenummer(arbeitsadressenummer).withArbeitsadressestrasse(arbeitsadressestrasse).withArbeitsadressestadt(arbeitsadressestadt).withPlace_idWork(place));


            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
// Die Function wird genutzt in der HomeAdressPage

    @GetMapping("/placeidhome/{username}/{wohnadressestadt}/{wohnadressestrasse}/{wohnadressenummer}")
    public ResponseEntity<PlaceIdResponse> changeAdressHome(@PathVariable String username, @PathVariable String wohnadressestadt,@PathVariable String wohnadressestrasse,@PathVariable String wohnadressenummer) {
        Optional<MongoUser> user = service.findByUsername(username);

        if (user.isPresent()) {
            MongoUser mongoUser = user.get();


            PlaceIdResponse response = service.Place_Id(mongoUser.wohnadressenummer, mongoUser.wohnadressestrasse, mongoUser.wohnadressestadt);
            String place = response.getPlaceId();

            userRepo.save(mongoUser.withWohnadressenummer(wohnadressenummer).withWohnadressestrasse(wohnadressestrasse).withWohnadressestadt(wohnadressestadt).withPlace_idHome(place));


            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }


// User Time


    @PostMapping("/time/{username}/{startZeit}/{endZeit}/{vorbereitungsZeit}/{maximalweckZeit}")
    public ResponseEntity<MongoUser> saveTime(@PathVariable String username, @PathVariable String startZeit, @PathVariable String endZeit,@PathVariable String vorbereitungsZeit, @PathVariable String maximalweckZeit ) throws
            InstantiationException, IllegalAccessException {

        Optional<MongoUser> user = service.findByUsername(username);
        System.out.println(maximalweckZeit);

        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            userRepo.save(mongoUser.withEndZeit(endZeit).withStartZeit(startZeit).withVorbereitungsZeit(vorbereitungsZeit).withMaximalweckzeit(maximalweckZeit));

            return ResponseEntity.ok(mongoUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    // wird vom Wecker verwendet
    @GetMapping("/weckzeit/{username}")
    public ResponseEntity<Integer> Weckzeit(@PathVariable String username) throws InstantiationException, IllegalAccessException, InterruptedException {
        Optional<MongoUser> user = service.findByUsername(username);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            //Daten werden vom User gezogen
            int duration = (mongoUser.duration);
            String startzeit = mongoUser.getStartZeit();
            String endzeit = mongoUser.getEndZeit();
            String fahrzeit = mongoUser.getMaximalweckzeit();
            String vorbereitungszeit = mongoUser.getVorbereitungsZeit();
            int durationOnTraffic = findDurationTest(username).getBody();

            // Berechnung
            int sum = ((duration) / 60);
            int vorbereitungszeit1 = Integer.parseInt(vorbereitungszeit);
            int startZeitMin = Service.StringToMinutes(startzeit);
            int endZeitMin = Service.StringToMinutes(startzeit);
            int zeitBisZurArbeit = (sum + vorbereitungszeit1);
            int weckzeitohneVerkehr = startZeitMin - sum;
            int weckZeitMitVerkehr = weckzeitohneVerkehr - durationOnTraffic;

            System.out.println(startzeit);
            System.out.println(endzeit);
            System.out.println(fahrzeit);
            System.out.println(vorbereitungszeit);
            System.out.println(duration);
            System.out.println(durationOnTraffic);
            System.out.println(sum);


            return ResponseEntity.ok(weckZeitMitVerkehr);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Teure Variante
    //wird noch nicht verwendet
    @GetMapping("/traffictime/{username}")
    public ResponseEntity<ResponseDuration> findDurationTimeTraffic(@PathVariable String username) throws InterruptedException {
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
                System.out.println("Differenz: " + summe / 60 + " Minuten");
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
    // hier wird nur zusätzliche Zeit zurückgegeben die der user mehr benötigt
    @GetMapping("/duration/{username}")
    public ResponseEntity<Integer> findDuration(@PathVariable String username) throws
            InstantiationException, IllegalAccessException {

        Optional<MongoUser> user = service.findByUsername(username);

        if (user.isPresent()) {
            MongoUser mongoUser = user.get();

            ResponseDuration inhalt = service.duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);
            System.out.println(inhalt);
            int sum;
            int traffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
            int notraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getValue();
            System.out.println(traffic);
            System.out.println(notraffic);

            sum = (traffic - notraffic) / 60;
            System.out.println(sum);


            return ResponseEntity.ok(sum);

        } else {
            return ResponseEntity.notFound().build();
        }

    }


    // wird vom Register verwendet
    @GetMapping("/anfragen/{username}")
    public ResponseEntity<ResponseDuration> anfragen(@PathVariable String username) throws InterruptedException {
        //Username finden
        Optional<MongoUser> user = service.findByUsername(username);
      if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            System.out.println(username);
            //placeidHome holen

            PlaceIdResponse placeipHome = service.Place_Id(mongoUser.getWohnadressestadt(), mongoUser.getWohnadressestrasse(), mongoUser.getWohnadressenummer());
            //Placeid Speichern

            if (placeipHome != null) {

                userRepo.save(mongoUser.withPlace_idHome(placeipHome.getPlaceId()).withPlace_idWork(placeipHome.getPlaceId()));
                Thread.sleep(3000);
                System.out.println(placeipHome);
            }
          //placeidHome holen

            PlaceIdResponse placeipWork = service.Place_Id(mongoUser.getArbeitsadressestadt(), mongoUser.getArbeitsadressestrasse(), mongoUser.getArbeitsadressenummer());

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
                System.out.println("Differenz: " + summe / 60 + " Minuten");

                //Speichern der PlaceId home
                userRepo.save(mongoUser.withPlace_idHome(placeipHome.getPlaceId()).withPlace_idWork(placeipWork.getPlaceId()).withDuration(Integer.parseInt(String.valueOf(timewhitouttraffic))));

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

    @GetMapping("/vorbereitungszeitTest/{vorbereitungszeit}/{username}")
    public ResponseEntity<String> findvortest(@PathVariable String username, @PathVariable String vorbereitungszeit) {
        Optional<MongoUser> user = service.findByUsername(username);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            userRepo.save(mongoUser.withVorbereitungsZeit(vorbereitungszeit));

            System.out.println(findVorbereitungsZeit(toString()));
            return ResponseEntity.ok(mongoUser.getVorbereitungsZeit());
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


    // da die Registrierung von der vorbereitungszeit nicht geht ist die Funktion nur ein uebergang
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

}



















