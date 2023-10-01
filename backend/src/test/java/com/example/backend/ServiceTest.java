package com.example.backend;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;



class ServiceTest {

    @Test
    void findByUsername() {

    }

    @Test
    void timeWithoutTraffic() {
    }

    @Test
    void place_Id() {
    }

    @Test
    void duration() {
    }



    @Test
    void deleteUser() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void stringToMinutes() {
    }

    @Test
    void placeidAbfragenHome() {
    }

    @Test
    void placeidAbfragenWork() {
    }

    @Test
    void anfragen() {
    }

    @Test
    void durationMinut() {
    }

    @Test
    void saveUserTest(){

        //Given

        MongoUser user = new MongoUser();
        MongoUserRepo mongoUserRepo=mock(MongoUserRepo.class);//Simulation moc soll repo Simulieren
        Service service = new Service(mongoUserRepo);// erstellung Service
        when(mongoUserRepo.findMongoUserByUsername("Steve")).thenReturn(Optional.ofNullable(user.withUsername("Steve").withEmail("sgmd").withPassword("1")));
        //WHEN
        MongoUser actual =  service.saveUser(user.withUsername("Steve").withEmail("sgmd").withPassword("1"));
        //Then
        assertEquals(user.withUsername("Steve").withEmail("sgmd").withPassword("1"),actual);




    }
}