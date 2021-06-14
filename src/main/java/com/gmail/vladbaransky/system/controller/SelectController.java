package com.gmail.vladbaransky.system.controller;

import com.gmail.vladbaransky.system.service.model.RoleEnum;
import com.gmail.vladbaransky.system.service.model.StatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SelectController {
    @GetMapping("/role")
    public RoleEnum[] getRole() {
        return RoleEnum.values();
    }

    @GetMapping("/status")
    public StatusEnum[] getStatus() {
        return StatusEnum.values();
    }
}
