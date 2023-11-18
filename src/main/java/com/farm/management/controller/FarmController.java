package com.farm.management.controller;

import com.farm.management.exception.AppException;
import com.farm.management.model.*;
import com.farm.management.repository.FarmRepository;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class FarmController {

    private FarmService farmService;

    @PostMapping("/farm")
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm, @CurrentUser UserPrincipal currentUser){
        Farm savedFarm = farmService.createFarm(farm);
        return new ResponseEntity<>(savedFarm, HttpStatus.CREATED);
    }
}