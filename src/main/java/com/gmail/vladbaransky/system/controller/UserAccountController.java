package com.gmail.vladbaransky.system.controller;

import com.gmail.vladbaransky.system.service.UserAccountService;
import com.gmail.vladbaransky.system.service.model.UserAccountAddDTO;
import com.gmail.vladbaransky.system.service.model.UserAccountRefDTO;
import com.gmail.vladbaransky.system.service.model.ValidationError;
import com.gmail.vladbaransky.system.service.util.ValidationErrorBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserAccountAddDTO>> getUsers() {
        return ResponseEntity.ok(userAccountService.getUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserAccountAddDTO> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(userAccountService.getUserById(id));
    }

    @GetMapping("/user/exists")
    public ResponseEntity<String> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userAccountService.getUserByUsername(username).getUsername());
    }


    @GetMapping("/cur")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> addUser(@Valid UserAccountAddDTO userAccountAddDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        userAccountService.addUser(userAccountAddDTO);
        return ResponseEntity.ok(userAccountService.getUsers());

    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> editUser(@Valid UserAccountRefDTO userAccountRefDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        userAccountService.editUser(userAccountRefDTO);
        return ResponseEntity.ok(userAccountService.getUsers());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(Exception exception) {
        return new ValidationError(exception.getMessage());
    }

}
