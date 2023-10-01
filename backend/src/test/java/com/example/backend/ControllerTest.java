package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.test.web.servlet.MockMvc;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


@SpringBootTest

//@AutoConfigureMockMvc;
class ControllerTest {


    private MockMvc mvc;
    @Autowired
    private final Controller controller;

    @Autowired
    private MongoUserRepo mongoUserRepo;

    ControllerTest(MockMvc mvc, Controller controller) {
        this.mvc = mvc;
        this.controller = controller;
    }


    @Test
    public void testChangeAddress() throws Exception {
        MongoUserRepo mongoUserRepo = mock(MongoUserRepo.class);



    }

    @Test
    void saveTime() {
        MongoUserRepo mongoUserRepo = mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);

    }

    @Test
    void weckzeit() {
        MongoUserRepo mongoUserRepo = mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);
    }

    @Test
    void findDurationTimeTraffic() {
        MongoUserRepo mongoUserRepo = mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);
    }

    @Test
    void findDuration() {
    }
    // password konnte nicht umgewandelt werden
    @Test
    void saveUser() {
        MongoUserRepo mongoUserRepo = mock(MongoUserRepo.class);
        Service service = new Service(mongoUserRepo);

    }


    @Test
    void delete() {
    }


    @Test
    public void test_returns_empty_string_if_no_authenticated_user() {

        String expectedName = "JohnDoe";
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(expectedName, null));
        String actualName = controller.login();
        assertEquals(expectedName, actualName);
    }
}






