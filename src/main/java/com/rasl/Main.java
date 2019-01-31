package com.rasl;


import com.rasl.repository.UserAccountRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        UserAccountRepositoryImpl userAccountRepository = new UserAccountRepositoryImpl();
        System.out.println(userAccountRepository.findByLogin("login2"));
        System.out.println(userAccountRepository.updateLastName(2, "test"));
        System.out.println(userAccountRepository.updateLastName("login3", "eee"));
    }
}
