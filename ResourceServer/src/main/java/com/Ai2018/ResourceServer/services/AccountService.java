package com.Ai2018.ResourceServer.services;

import com.Ai2018.ResourceServer.models.Account;
import com.Ai2018.ResourceServer.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountException;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
       Account account =accountRepository.findAccountByUsername(username);
       if(account==null) {
           throw new UsernameNotFoundException("User not found");
       }
       return account;
    }
    public Account register(Account account) throws Exception{
        Account existingAccount = accountRepository.findAccountByUsername(account.getUsername());
        if (existingAccount == null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            return accountRepository.save(account);
        } else {
            throw new Exception("Username already taken");
        }
    }
    @Transactional
    public Account update(Account account) throws Exception {
        Account existingAccount = accountRepository.findAccountByUsername(account.getUsername());
        if (existingAccount != null) {
            return accountRepository.save(account);
        } else {
            throw new Exception("Account does not exist");
        }
    }
}
