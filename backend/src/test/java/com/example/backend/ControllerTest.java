package com.example.backend;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.testng.AssertJUnit.assertEquals;
import static sun.security.jgss.GSSUtil.login;

@SpringBootTest

//@AutoConfigureMockMvc;
class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MongoUserRepo mongoUserRepo;



    @Test
    void testChangeAddress() {
        // Erstelle eine Instanz deines AddressService


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

    @Test
    public void test_successful_logout() {
        HttpSession httpSession = new MockHttpSession();
        String result = (login(httpSession));
        assertEquals("logged out", result);
    }




}