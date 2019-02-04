package com.rasl.controller;

import com.rasl.entity.UserAccount;
import com.rasl.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserAccountController {

    private UserAccountServiceImpl userAccountService;

    @Autowired
    public UserAccountController(UserAccountServiceImpl userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public @ResponseBody List<UserAccount> list(){
        return userAccountService.list();
    }

    @GetMapping("/{id}")
    public @ResponseBody UserAccount getById(@PathVariable Integer id){
        return userAccountService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void newUserAccount(@RequestBody UserAccount userAccount){
        userAccountService.save(userAccount);
    }



    @PutMapping("/{id}")
    public void editUserAccount(@PathVariable Integer id, @RequestBody UserAccount userAccount){
        UserAccount newUserAccount = userAccountService.getById(id);
        if(!isNullOrEmpty(userAccount.getLogin())){
            newUserAccount.setLogin(userAccount.getLogin());
        }
        if(!isNullOrEmpty(userAccount.getFirstName())){
            newUserAccount.setFirstName(userAccount.getFirstName());
        }
        if(!isNullOrEmpty(userAccount.getMiddleName())){
            newUserAccount.setMiddleName(userAccount.getMiddleName());
        }
        if(!isNullOrEmpty(userAccount.getLastName())){
            newUserAccount.setLastName(userAccount.getLastName());
        }

        userAccountService.save(newUserAccount);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userAccountService.deleteById(id);
    }

    private boolean isNullOrEmpty(String str){
       return str == null || str.isEmpty();
    }
}
