package com.rasl.controller;

import com.rasl.Application;
import com.rasl.entity.UserAccount;
import com.rasl.repository.UserAccountRepository;
import com.rasl.service.UserAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        Application.class
})

//@Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserAccountControllerTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    public void listTest() {
        System.out.println(userAccountService.list().size());
    }

    @Test
    public void getByIdTest() {
    }

    @Test
    public void newUserAccountTest() {
    }
}