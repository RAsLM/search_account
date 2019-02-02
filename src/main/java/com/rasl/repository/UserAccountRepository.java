package com.rasl.repository;

import com.rasl.searchSecurityAccount.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    void delete(Integer id);
}
