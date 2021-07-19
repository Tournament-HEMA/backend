package com.example.backend.controller;

import com.example.backend.model.Club;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/auth/")
public class AuthController {



//    @GetMapping
//    public ResponseEntity<?> autorization() {
//        final List<Club> clubs = clubService.findAll();
//
//        return clubs != null &&  !clubs.isEmpty()
//                ? new ResponseEntity<>(clubs, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
