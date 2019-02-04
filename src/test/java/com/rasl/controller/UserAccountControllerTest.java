package com.rasl.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasl.entity.UserAccount;
import com.rasl.service.UserAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccountControllerTest {

    private static final String URL = "/users";

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void listTest() throws IOException {

        ResponseEntity<List<UserAccount>> response = restTemplate.exchange(
                url(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserAccount>>(){}
        );

        List<UserAccount> actualUsers = response.getBody();

        Resource resource = resourceLoader.getResource("classpath:/data/user_accounts.json");

        List<UserAccount> expectedUsers = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<UserAccount>>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void getByIdTest() throws IOException {
        ResponseEntity<UserAccount> response = restTemplate.getForEntity(url(1), UserAccount.class);

        UserAccount actualUser = response.getBody();

        Resource resource = resourceLoader.getResource("classpath:/data/user_account.json");
        UserAccount expectedUser = objectMapper.readValue(resource.getInputStream(), UserAccount.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void newUserAccountTest() {
        UserAccount expectedUser = UserAccount.builder()
                .userAccountId(1000)
                .login("test1")
                .firstName("test1")
                .middleName("test1")
                .lastName("test1")
                .build();
        ResponseEntity<UserAccount> response = restTemplate.postForEntity(url(), expectedUser, UserAccount.class);

        response = restTemplate.getForEntity(url(1000), UserAccount.class);

        UserAccount actualUser = response.getBody();

        assertEquals(expectedUser, actualUser);

        restTemplate.delete(url(1000));
    }

    @Test
    public void deleteTest(){
        restTemplate.delete(url(5));
       ResponseEntity<UserAccount> response = restTemplate.getForEntity(url(5), UserAccount.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void editUserAccount(){
        UserAccount expectedUser = UserAccount.builder()
                .login("login1")
                .lastName("testEdit1")
                .build();

        restTemplate.put(url(1), expectedUser, UserAccount.class);
        ResponseEntity<UserAccount> response = restTemplate.getForEntity(url(1), UserAccount.class);
        UserAccount actualUser = response.getBody();
        assertEquals(expectedUser.getLastName(), actualUser.getLastName());
    }

    private String url(){
        return URL;
    }

    private String url(Integer id) {
        return URL + "/" + id;
    }
}