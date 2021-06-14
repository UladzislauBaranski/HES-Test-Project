package com.gmail.vladbaransky.system.service.util;

import com.gmail.vladbaransky.system.repository.model.UserAccount;
import com.gmail.vladbaransky.system.service.model.UserAccountAddDTO;
import com.gmail.vladbaransky.system.service.model.UserAccountRefDTO;

public class UserAccountRefConverter {
    public static UserAccount getObjectFromDTO(UserAccountRefDTO userAccountRefDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountRefDTO.getId());
        userAccount.setUsername(userAccountRefDTO.getUsername());
        userAccount.setPassword(userAccountRefDTO.getPassword());
        userAccount.setFirstName(userAccountRefDTO.getFirstName());
        userAccount.setLastName(userAccountRefDTO.getLastName());
        userAccount.setRole(userAccountRefDTO.getRole());
        userAccount.setStatus(userAccountRefDTO.getStatus());
       // userAccount.setCreatedAt(userAccountDTO.getCreatedAt());
        return userAccount;
    }

    public static UserAccountRefDTO getDTOFromObject(UserAccount userAccount) {
        UserAccountRefDTO userAccountRefDTO = new UserAccountRefDTO();
        userAccountRefDTO.setId(userAccount.getId());
        userAccountRefDTO.setUsername(userAccount.getUsername());
        userAccountRefDTO.setPassword(userAccount.getPassword());
        userAccountRefDTO.setFirstName(userAccount.getFirstName());
        userAccountRefDTO.setLastName(userAccount.getLastName());
        userAccountRefDTO.setRole(userAccount.getRole());
        userAccountRefDTO.setStatus(userAccount.getStatus());
        userAccountRefDTO.setCreatedAt(userAccount.getCreatedAt());
        return userAccountRefDTO;

    }
}
