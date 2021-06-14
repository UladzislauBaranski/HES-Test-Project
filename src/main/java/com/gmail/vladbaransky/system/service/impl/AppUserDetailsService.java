package com.gmail.vladbaransky.system.service.impl;

import com.gmail.vladbaransky.system.service.UserAccountService;
import com.gmail.vladbaransky.system.service.model.AppUserPrincipal;
import com.gmail.vladbaransky.system.service.model.UserAccountAddDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final UserAccountService userAccountService;

    public AppUserDetailsService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountAddDTO user = userAccountService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        logger.info("User '" + username + "' was found");
        return new AppUserPrincipal(user);
    }
}
