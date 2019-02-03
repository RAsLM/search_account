package com.rasl.controller;

import com.rasl.entity.UserAccount;
import com.rasl.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user_account")
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

//    @PutMapping(value = "/user_account/{userAccount}")
//    public String saveUserAccount(@PathVariable UserAccount userAccount){
//
//        return "redirect:/user_account/list";
//    }
//
//    @RequestMapping("/user_account/{id}")
//    public String editUserAccount(@PathVariable Integer id){
//
//        return "user_account/new_entry";
//
//    }
//
//    @DeleteMapping(value = "/user_account/{id}")
//    public String delete(@PathVariable Integer id){
//        userAccountService.deleteById(id);
//        return "redirect:/user_account/list";
//    }

}
