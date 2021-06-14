package com.gmail.vladbaransky.system.service;

import com.gmail.vladbaransky.system.service.model.UserAccountAddDTO;
import com.gmail.vladbaransky.system.service.model.UserAccountRefDTO;

import java.util.List;

public interface UserAccountService {
    List<UserAccountAddDTO> getUsers();

    UserAccountAddDTO getUserByUsername(String username);

    UserAccountAddDTO getUserById(Long id);

    UserAccountAddDTO addUser(UserAccountAddDTO userAccountAddDTO);

    UserAccountRefDTO editUser(UserAccountRefDTO userAccountRefDTO);
}
