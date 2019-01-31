package com.rasl.repository;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountRepositoryImplTest {

    UserAccountRepositoryImpl repository = new UserAccountRepositoryImpl();

    @Test
    public void findByLoginTest() {
        String login = "login2";
        assertEquals(login, repository.findByLogin(login).getLogin());
    }

    @Test
    public void updateLastNameTest() {
        String updateLastName = "newLastName";
        repository.updateLastName(2, updateLastName);
        assertEquals(updateLastName, repository.findByLogin("login2").getLastName());
    }

    @Test
    public void updateLastName1Test() {
        String updateLastName = "newLastName";
        repository.updateLastName("login3", updateLastName);
        assertEquals(updateLastName, repository.findByLogin("login3").getLastName());
    }
}