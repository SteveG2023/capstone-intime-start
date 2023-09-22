package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor




@org.springframework.stereotype.Service


public class Service implements UserDetailsService {






    public Optional<MongoUser> findByUsername(String username) {
        return repo.findMongoUserByUsername( username);
    }

    private final MongoUserRepo repo;


    static WebClient webClient = WebClient.create("https://maps.googleapis.com/maps/api");

    String API_Key = "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8";


    public ResponseDuration timeWithoutTraffic(String place_idH, String place_idW) {

        return Objects.requireNonNull(webClient.get()

                        // .uri("/directions/json?departure_time=now&destination=place_id:"+place_idW+"&origin=place_id:"+place_idH+"&key="+API_Key)
                        .uri("/directions/json?departure_time=now&destination=city=Hamburg&origin=city=Magdeburg&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .toEntity(ResponseDuration.class)
                        .block())
                .getBody();
    }


    // Diese Application funktioniert////////////////////////////////////////////

    public PlaceIdResponse Place_Id(String adresse, String street, String number) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address=" + adresse + "=street=" + street + "=number=" + number + "&key=" + API_Key)

                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                        .getBody();
    }

    ResponseDuration duration(String citywork, String streetwork, String numberwork, String cityhome, String streethome, String numberhome) {
       // WebClient responseEntity =  webClient;

        return Objects.requireNonNull(webClient.get()
                .uri("/directions/json?departure_time=now&destination=city="+cityhome+"=street="+streethome+"number="+numberhome+"&origin=city=+"+citywork+"=street=+"+streetwork+"number="+numberwork+"&key=AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8")
                .retrieve()
                .toEntity(ResponseDuration.class)
                .block())
                .getBody();

    }
    //String API_Key = "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8";


    public  MongoUser saveUser(MongoUser user) {
        if (repo.findMongoUserByUsername(user.getUsername()).equals(user.getUsername())&& repo.findMongoUserByUsername(user.getEmail()).equals(user.getEmail())) {
            throw new IllegalArgumentException("Username already taken");
        }

        PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        repo.save(user.withPassword(encoder.encode(user.getPassword())));
        return user;
    }


    public void deleteUser(String username) {
        if (repo.existsById(username)) {
            repo.deleteById(username);

        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser mongoUser = repo.findMongoUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
        return new User(mongoUser.getUsername(), mongoUser.getPassword(), List.of());
    }



        public static int StringToMinutes(String timeString) {
            try {
                // Trennen Sie die Stunden und Minuten aus dem Zeit-String
                String[] parts = timeString.split(":");

                // Überprüfen Sie, ob der String im korrekten Format (hh:mm) ist
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Ungültiges Zeitformat. Verwenden Sie hh:mm.");
                }

                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);

                // Konvertieren Sie Stunden und Minuten in Gesamtminuten
                return hours * 60 + minutes;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Ungültige Zeitangabe. Stellen Sie sicher, dass Stunden und Minuten numerisch sind.");
            }
        }

        public static void main(String[] args) {
            String timeString = "14:30";
            int startZeitUhr = StringToMinutes(timeString);
            System.out.println("Uhrzeit in Minuten: " + startZeitUhr);
        }
        public ResponseEntity<PlaceIdResponse> placeidAbfragenHome( String username,String wohnadressestadt,String wohnadressestrasse, String wohnadressenummer){
            Optional<MongoUser> user = findByUsername(username);

            if (user.isPresent()) {
                MongoUser mongoUser = user.get();

                PlaceIdResponse response = Place_Id(mongoUser.wohnadressenummer, mongoUser.wohnadressestrasse, mongoUser.wohnadressestadt);
                repo.save(mongoUser.withWohnadressenummer(wohnadressenummer).withWohnadressestrasse(wohnadressestrasse).withWohnadressestadt(wohnadressestadt).withPlace_idHome(response.getPlaceId()));
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }





        public ResponseEntity<PlaceIdResponse> placeidAbfragenWork (String username,String arbeitsadressestadt,String arbbeitsadressestrasse,String arbeitsadressenummer){

            Optional<MongoUser> user = findByUsername(username);
            if (user.isPresent()) {
                MongoUser mongoUser = user.get();
                PlaceIdResponse response = Place_Id(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressestadt);
                repo.save(mongoUser.withArbeitsadressestadt(arbeitsadressestadt).withArbeitsadressestrasse(arbbeitsadressestrasse).withArbeitsadressenummer(arbeitsadressenummer).withPlace_idWork(response.getPlaceId()));

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }



        public ResponseEntity<ResponseDuration> anfragen(String username) throws InterruptedException {

            Optional<MongoUser> user = findByUsername(username);
            if (user.isPresent()) {
                MongoUser mongoUser = user.get();
                System.out.println(username);
                //placeidHome holen

                PlaceIdResponse placeipHome = Place_Id(mongoUser.getWohnadressestadt(), mongoUser.getWohnadressestrasse(), mongoUser.getWohnadressenummer());
                //Placeid Speichern

                if (placeipHome != null) {

                    repo.save(mongoUser.withPlace_idHome(placeipHome.getPlaceId()).withPlace_idHome(placeipHome.getPlaceId()));
                    Thread.sleep(3000);
                    System.out.println(placeipHome);
                }
                //placeidHome holen

                PlaceIdResponse placeipWork = Place_Id(mongoUser.getArbeitsadressestadt(), mongoUser.getArbeitsadressestrasse(), mongoUser.getArbeitsadressenummer());

                if (placeipWork != null) {

                    Thread.sleep(3000);
                    System.out.println(placeipWork);
                }
                ResponseDuration inhalt = duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);

                if (inhalt != null) {
                    int timewithtraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
                    int timewhitouttraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getValue();

                    System.out.println("Zeit mit Verkehr: " + timewithtraffic);

                    int summe = timewithtraffic - timewhitouttraffic;

                    System.out.println("Zeit ohne Verkehr: " + timewhitouttraffic);
                    System.out.println("Differenz: " + summe / 60 + " Minuten");

                    //Speichern der PlaceId home
                    repo.save(mongoUser.withPlace_idHome(placeipHome.getPlaceId()).withPlace_idWork(placeipWork.getPlaceId()).withDuration(Integer.parseInt(String.valueOf(timewhitouttraffic))));

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

        public ResponseEntity<Integer> durationMinute (String username){
            Optional<MongoUser> user = findByUsername(username);

            if (user.isPresent()) {
                MongoUser mongoUser = user.get();

                ResponseDuration inhalt = duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);
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

        public ResponseEntity<ResponseDuration> trafficTime (String username) throws InterruptedException {

            Optional<MongoUser> user = findByUsername(username);

            if (user.isPresent()) {
                MongoUser mongoUser = user.get();
                //hole die Zeit  mit Verkehr
                ResponseDuration inhalt = duration(mongoUser.arbeitsadressestadt, mongoUser.arbeitsadressestrasse, mongoUser.arbeitsadressenummer, mongoUser.wohnadressestadt, mongoUser.wohnadressestrasse, mongoUser.wohnadressenummer);


                if (inhalt != null) {
                    int timewithtraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
                    int timewhitouttraffic = inhalt.getRoutes().get(0).getLegs().get(0).getDuration().getValue();

                    System.out.println("Zeit mit Verkehr: " + timewithtraffic);

                    int summe = timewithtraffic - timewhitouttraffic;

                    System.out.println("Zeit ohne Verkehr: " + timewhitouttraffic);
                    System.out.println("Differenz: " + summe / 60 + " Minuten");
                    repo.save(mongoUser.withDuration(Integer.parseInt(String.valueOf(timewhitouttraffic))));

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


    public ResponseEntity<Integer> weckzeitTime(String username) throws InterruptedException {

        Optional<MongoUser> user = findByUsername(username);
        if (user.isPresent()) {
            MongoUser mongoUser = user.get();
            //Daten werden vom User gezogen
            int duration = (mongoUser.getDuration());
            String startzeit = mongoUser.getStartZeit();

            //String endzeit = mongoUser.getEndZeit();
           // String fahrzeit = mongoUser.getMaximalweckZeit();
            String vorbereitungszeit = mongoUser.getVorbereitungsZeit();
            ResponseDuration durationOnTraffic = trafficTime(username).getBody();
            int duration1 = durationOnTraffic.getRoutes().get(0).getLegs().get(0).getDuration_in_traffic().getValue();
            int verkehr = duration-duration1;

            //30 min
            //ResponseDuration durationOnTraffic = findDurationTimeTraffic(username).getBody();
            Integer durationFind= durationMinute(username).getBody();
            // Berechnung
            int sum = ((duration) / 60);                                    //48
            int vorbereitungszeitmin = Integer.parseInt(vorbereitungszeit); //30
            int startZeitMin = Service.StringToMinutes(startzeit);         //11:46
            int zeitBisZurArbeit = (sum+vorbereitungszeitmin+verkehr);  //Fahrzeit+vorbereitungszeit  30 min + 125min  147 min
            int weckzeitohneVerkehr = (startZeitMin-zeitBisZurArbeit);
            // Startzeit =706-78 = 628
            int weckZeitMitVerkehr = (weckzeitohneVerkehr );




            return ResponseEntity.ok(weckZeitMitVerkehr);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    public ResponseEntity<MongoUser> testzeit (String username, String endZeit, String startZeit, String vorbreitungsZeit, String maximalweckZeit)   {

        Optional<MongoUser> user = findByUsername(username);
        System.out.println(maximalweckZeit
        );

        System.out.println(startZeit);
        System.out.println(endZeit);
        System.out.println(vorbreitungsZeit);

        if (user.isPresent()) {
            System.out.println("bin drinne ");
            MongoUser mongoUser = user.get();
            repo.save(mongoUser.withEndZeit(endZeit).withStartZeit(startZeit).withVorbereitungsZeit(vorbreitungsZeit).withMaximalweckZeit(maximalweckZeit));

            return ResponseEntity.ok(mongoUser);
        } else {
            return ResponseEntity.notFound().build();
        }


    }


}










