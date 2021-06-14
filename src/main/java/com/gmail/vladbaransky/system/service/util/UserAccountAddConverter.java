package com.gmail.vladbaransky.system.service.util;

import com.gmail.vladbaransky.system.repository.model.UserAccount;
import com.gmail.vladbaransky.system.service.model.UserAccountAddDTO;

public class UserAccountAddConverter {
    public static UserAccount getObjectFromDTO(UserAccountAddDTO userAccountAddDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountAddDTO.getId());
        userAccount.setUsername(userAccountAddDTO.getUsername());
        userAccount.setPassword(userAccountAddDTO.getPassword());
        userAccount.setFirstName(userAccountAddDTO.getFirstName());
        userAccount.setLastName(userAccountAddDTO.getLastName());
        userAccount.setRole(userAccountAddDTO.getRole());
        userAccount.setStatus(userAccountAddDTO.getStatus());
       // userAccount.setCreatedAt(userAccountDTO.getCreatedAt());
        return userAccount;
    }

    public static UserAccountAddDTO getDTOFromObject(UserAccount userAccount) {
        UserAccountAddDTO userAccountAddDTO = new UserAccountAddDTO();
        userAccountAddDTO.setId(userAccount.getId());
        userAccountAddDTO.setUsername(userAccount.getUsername());
        userAccountAddDTO.setPassword(userAccount.getPassword());
        userAccountAddDTO.setFirstName(userAccount.getFirstName());
        userAccountAddDTO.setLastName(userAccount.getLastName());
        userAccountAddDTO.setRole(userAccount.getRole());
        userAccountAddDTO.setStatus(userAccount.getStatus());
        userAccountAddDTO.setCreatedAt(userAccount.getCreatedAt());
        return userAccountAddDTO;

    }
}
