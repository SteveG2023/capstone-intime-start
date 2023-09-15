package com.example.backend;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
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
        if (repo.findMongoUserByUsername(user.getUsername()).equals(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        repo.save(user.withPassword(encoder.encode(user.getPassword())));
        return user;
    }


    public void deleteUser(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);

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
            String timeString = "14:30"; // Beispiel-Zeit im Format "hh:mm"
            int startZeitUhr = StringToMinutes(timeString);
            System.out.println("Uhrzeit in Minuten: " + startZeitUhr);
        }








}










