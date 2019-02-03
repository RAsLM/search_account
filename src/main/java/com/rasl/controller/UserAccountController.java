package com.rasl.controller;

import com.rasl.entity.UserAccount;
import com.rasl.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAccountController {

    private UserAccountServiceImpl userAccountService;

    @Autowired
    public UserAccountController(UserAccountServiceImpl userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping("/user_account/list")
    public String list(Model model){

        return "user_account/list";
    }

    @RequestMapping("/user_account/details/{id}")
    public String card(@PathVariable Integer id, Model model){
        model.addAttribute("entry", userAccountService.getById(id));
        return "user_account/details";
    }

    @RequestMapping("/user_account/new_user_account")
    public String newIncomeEntry(Model model){

        return "user_account/new_entry";
    }

    @RequestMapping(value = "/user_account/save", method = RequestMethod.POST)
    public String saveEntry(UserAccount userAccount){

        return "redirect:/user_account/list";
    }

    @RequestMapping("/user_account/edit/{id}")
    public String editEntry(@PathVariable Integer id, Model model){

        return "user_account/new_entry";

    }

    @RequestMapping(value = "/user_account/delete/{id}")
    public String delete(@PathVariable Integer id){

        return "redirect:/user_account/list";
    }

}
