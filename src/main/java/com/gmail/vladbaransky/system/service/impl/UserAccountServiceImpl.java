package com.gmail.vladbaransky.system.service.impl;

import com.gmail.vladbaransky.system.config.PasswordEncoderConfig;
import com.gmail.vladbaransky.system.repository.UserAccountRepository;
import com.gmail.vladbaransky.system.repository.model.UserAccount;
import com.gmail.vladbaransky.system.service.UserAccountService;
import com.gmail.vladbaransky.system.service.model.UserAccountAddDTO;
import com.gmail.vladbaransky.system.service.model.UserAccountRefDTO;
import com.gmail.vladbaransky.system.service.util.UserAccountAddConverter;

import com.gmail.vladbaransky.system.service.util.UserAccountRefConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoderConfig passwordEncoderConfig) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }


    @Override
    public List<UserAccountAddDTO> getUsers() {
        List<UserAccount> usersFromDB = userAccountRepository.findAll();
        return usersFromDB.stream()
                .map(UserAccountAddConverter::getDTOFromObject)
                .collect(Collectors.toList());
    }

    @Override
    public UserAccountAddDTO getUserByUsername(String username) {
        UserAccount byUsername = userAccountRepository.findByUsername(username);
        if (byUsername != null) return UserAccountAddConverter.getDTOFromObject(byUsername);
        else return new UserAccountAddDTO();
    }

    @Override
    public UserAccountAddDTO getUserById(Long id) {
        UserAccount userByIdFromDB = userAccountRepository.findAllById(id);
        return UserAccountAddConverter.getDTOFromObject(userByIdFromDB);
    }

    @Transactional
    @Override
    public UserAccountAddDTO addUser(UserAccountAddDTO userAccountAddDTO) {
        UserAccount user = UserAccountAddConverter.getObjectFromDTO(userAccountAddDTO);
        String password = user.getPassword();
        String bCryptPassword = passwordEncoderConfig.passwordEncoder().encode(password);
        user.setPassword(bCryptPassword);
        UserAccount userFromDB = userAccountRepository.save(user);
        return UserAccountAddConverter.getDTOFromObject(userFromDB);
    }

    @Transactional
    @Override
    public UserAccountRefDTO editUser(UserAccountRefDTO userAccountRefDTO) {
        UserAccount user = UserAccountRefConverter.getObjectFromDTO(userAccountRefDTO);
        String password = user.getPassword();
        String bCryptPassword = passwordEncoderConfig.passwordEncoder().encode(password);
        user.setPassword(bCryptPassword);
        UserAccount userFromDB = userAccountRepository.save(user);
        return UserAccountRefConverter.getDTOFromObject(userFromDB);
    }
}
