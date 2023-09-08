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
import java.util.Optional;

import static com.example.backend.Service.webClient;

@Service
@RequiredArgsConstructor

public class MongoUserDetailService implements UserDetailsService {

    private final MongoUserRepo repo;
    // private final IdService idService;
    String API_Key = "AIzaSyACQwB6EJsUNDvda1Yxl9sbnF2Muwhi4v8";


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
    public PlaceIdResponse Place_Id(String adresse, String street, String number) {


        return Objects.requireNonNull(webClient.get()
                        .uri("/geocode/json?address=" + adresse + "=street=" + street + "=number=" + number + "&key=" + API_Key)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .toEntity(PlaceIdResponse.class)
                        .block())
                .getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser mongoUser = repo.findMongoUserByUsername(username)
                  .orElseThrow(()-> new UsernameNotFoundException("User with username: " + username + " not found"));
        return new User(mongoUser.getUsername(), mongoUser.getPassword(), List.of());
    }



    public Optional<MongoUser> findByUsername(String username) {
        return repo.findMongoUserByUsername( username);
    }




}
//SecurityProperties.User(mongoUser.getUsername(), mongoUser.getPassword(), List.of());
