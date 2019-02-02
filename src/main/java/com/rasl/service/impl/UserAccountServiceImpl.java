package com.rasl.service.impl;

import com.rasl.searchSecurityAccount.entity.UserAccount;
import com.rasl.searchSecurityAccount.repository.UserAccountRepository;
import com.rasl.searchSecurityAccount.service.PojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserAccountServiceImpl implements PojoService<UserAccount> {

    private final UserAccountRepository repository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserAccount> list() {
        return repository.findAll();
    }

    @Override
    public UserAccount getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return repository.save(userAccount);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
