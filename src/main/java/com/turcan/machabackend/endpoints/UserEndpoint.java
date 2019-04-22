package com.turcan.machabackend.endpoints;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turcan.machabackend.entity.User;
import com.turcan.machabackend.entity.UserRepository;

@RestController
@RequestMapping("/user")
public class UserEndpoint {

    private UserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEndpoint(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getUsername() == null || user.getPassword() == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        try {
        	applicationUserRepository.save(user);
        } catch (DataIntegrityViolationException e) {
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}