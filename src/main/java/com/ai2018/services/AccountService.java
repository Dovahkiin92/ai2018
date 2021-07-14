package com.ai2018.services;

import com.ai2018.models.Account;
import com.ai2018.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
    /*
            return new
                    User("user",
                            passwordEncoder.encode("password")
                            ,new ArrayList<>();*/
       Account account =accountRepository.findAccountByUsername(username);
        account.grantAuthority("USER");

       if(account==null) {
           throw new UsernameNotFoundException("User not found");
       }
       return account;
    }
}
