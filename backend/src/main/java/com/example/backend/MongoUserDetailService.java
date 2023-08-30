package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.backend.Service.webClient;

@Service
@RequiredArgsConstructor
public class MongoUserDetailService implements UserDetailsService {

    private final MongoUserRepo repo;
   // private final IdService idService;
   String API_Key = "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8";





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser mongoUser = repo.findMongoUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
        return new User(mongoUser.getUsername(), mongoUser.getPassword(), List.of());
    }




    public MongoUser saveUser(MongoUser user) {
        if (repo.findMongoUserByUsername(user.getUsername()).equals(user.getUsername())){
            throw new IllegalArgumentException("Username already taken");
        }
        PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        repo.save(user.withPassword(encoder.encode(user.getPassword())));
        return user;
    }

    public MongoUser deleteUser(MongoUser user) {
        if (repo.findMongoUserByUsername(user.getUsername()).equals(user.getUsername())){
            repo.delete(user);
        }

        return user;
    }
















    public MongoUser saveUserPlaceIdWork(MongoUser user,String arbeitsadresse,String arbeitstrasse,String arbeitsnummer) {
        if (repo.findMongoUserByUsername(user.getUsername()).equals(user.getUsername())){

            PlaceIdResponse placeIdResponse;
            placeIdResponse=Place_Id(arbeitsadresse,arbeitstrasse,arbeitsnummer);

            repo.save(user.withArbeitsadressestadt(arbeitsadresse));
            repo.save(user.withArbeitsadressestadt(arbeitstrasse));
            repo.save(user.withArbeitsadressestadt(arbeitsnummer));
            repo.save((user.withPlace_ipWork(String.valueOf(placeIdResponse))));






        }


        return user;
    }

    public MongoUser saveUserPlaceIdHome(MongoUser user,String wohnadressestadt,String wohnadressestrasse,String wohnadressenummer) {
        if (repo.findMongoUserByUsername(user.getUsername()).equals(user.getUsername())){

            PlaceIdResponse placeIdResponse;
            placeIdResponse = Place_Id(wohnadressestadt,wohnadressestrasse,wohnadressenummer);

            repo.save(user.withArbeitsadressestadt(wohnadressestadt));
            repo.save(user.withArbeitsadressestadt(wohnadressestrasse));
            repo.save(user.withArbeitsadressestadt(wohnadressenummer));
            repo.save((user.withPlace_ipHome(String.valueOf(placeIdResponse))));






        }


        return user;
    }



    public PlaceIdResponse Place_Id(String adresse , String street , String number) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address=" + adresse + "=street=" + street + "=number=" + number + "&key=" + API_Key)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                .getBody();
    }




















}