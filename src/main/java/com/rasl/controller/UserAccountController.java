package com.rasl.controller;

import com.rasl.entity.UserAccount;
import com.rasl.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserAccountController {

    private UserAccountServiceImpl userAccountService;

    @Autowired
    public UserAccountController(UserAccountServiceImpl userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/user_account/")
    public String list(Model model){
        model.addAttribute("userAccountLis", userAccountService.list());
        return "user_account/list";
    }

    @GetMapping("/user_account/{id}")
    public String getById(@PathVariable Integer id, Model model){
        model.addAttribute("userAccount", userAccountService.getById(id));
        return "user_account/details";
    }

    @PostMapping("/user_account/")
    public String newUserAccount(Model model){

        return "user_account/new_user_account";
    }

    @PutMapping(value = "/user_account/{userAccount}")
    public String saveUserAccount(@PathVariable UserAccount userAccount){

        return "redirect:/user_account/list";
    }

    @RequestMapping("/user_account/{id}")
    public String editUserAccount(@PathVariable Integer id, Model model){

        return "user_account/new_entry";

    }

    @DeleteMapping(value = "/user_account/{id}")
    public String delete(@PathVariable Integer id){
        userAccountService.deleteById(id);
        return "redirect:/user_account/list";
    }

}
