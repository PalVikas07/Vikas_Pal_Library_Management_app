package com.mibrary.management.app.controller;

import com.mibrary.management.app.dto.RegisterRequestDTO;
import com.mibrary.management.app.entity.User;
import com.mibrary.management.app.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("haseRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
    }

}
