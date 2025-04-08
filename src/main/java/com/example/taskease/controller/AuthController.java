// // In AuthController.java
package com.example.taskease.controller;

import com.example.taskease.dto.LoginRequest;
import com.example.taskease.dto.RegisterRequest;
import com.example.taskease.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://35.170.182.117", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
// @CrossOrigin(origins = "*")

public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request);
            // Return a JSON object with the token:
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpStatus;
// import java.util.Map;
// import java.util.Collections;
// import org.springframework.beans.factory.annotation.Autowired;
// import com.example.taskease.service.IAuthService;
// import com.example.taskease.dto.RegisterRequest;
// import com.example.taskease.dto.LoginRequest;

// @RestController
// @RequestMapping("/api/auth")
// @CrossOrigin(origins = "http://35.170.182.117", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
// public class AuthController {

//     @Autowired
//     private IAuthService authService;

//     @PostMapping("/register")
//     public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
//         try {
//             authService.register(request);
//             return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
//         } catch (RuntimeException e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                     .body(Collections.singletonMap("error", e.getMessage()));
//         }
//     }

//     @PostMapping("/login")
//     public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
//         try {
//             String token = authService.login(request);
//             return ResponseEntity.ok(Collections.singletonMap("token", token));
//         } catch (RuntimeException e) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                     .body(Collections.singletonMap("error", e.getMessage()));
//         }
//     }
// }
