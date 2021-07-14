package com.ai2018.repositories;

import com.ai2018.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account,String> {
    Account findAccountByUsername(String username);


    List<Account> findAll();
    Account save(Account account);
}


