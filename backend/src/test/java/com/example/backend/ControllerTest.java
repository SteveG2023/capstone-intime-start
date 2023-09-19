package com.example.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest

//@AutoConfigureMockMvc;
class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MongoUserRepo mongoUserRepo;



    @Test
    void changeAdressWork(String berlin) {
        MongoUserRepo mongoUserRepo = Mockito.mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);

        Mockito.verify("ChIJuRMYfoNhsUcRoDrWe_I9JgQ");
    }

    @Test
    void changeAdressHome() {
        MongoUserRepo mongoUserRepo = Mockito.mock(MongoUserRepo.class);

    }

    @Test
    void saveTime() {
        MongoUserRepo mongoUserRepo = Mockito.mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);

    }

    @Test
    void weckzeit() {
        MongoUserRepo mongoUserRepo = Mockito.mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);
    }

    @Test
    void findDurationTimeTraffic() {
        MongoUserRepo mongoUserRepo = Mockito.mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);
    }

    @Test
    void findDuration() {
    }
    // password konnte nicht umgewandelt werden
    @Test
    void saveUser() {
        MongoUserRepo mongoUserRepo = Mockito.mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);

    }


    @Test
    void delete() {
    }

    @Test
    void testLogin() {
    }


}