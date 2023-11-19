package com.farm.management.controller;

import com.farm.management.model.Farm;
import com.farm.management.model.User;
import com.farm.management.model.Userfarm;
import com.farm.management.payload.AssignRequest;
import com.farm.management.service.FarmService;
import com.farm.management.service.UserService;
import com.farm.management.service.UserfarmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserfarmController {

    private UserfarmService userfarmService;
    private UserService userService;
    private FarmService farmService;
}
