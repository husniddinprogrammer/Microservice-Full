package userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import userservice.security.JwtUtil;
import userservice.security.Token;
import userservice.security.UserMaxsus;
import userservice.security.UserProvider;
import userservice.service.UserService;
import userservice.service.dto.UserDTO;
import userservice.service.vm.UserVM;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UsersController {
    Logger logger = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public List<UserDTO> getAllUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception{
        UserDTO userDTO = userService.getCurrentUser();
        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);
        list.addAll(userService.getAllByResponsibleUser(userDTO));
        return list;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserMaxsus userMaxsus)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userMaxsus.getUsername(), userMaxsus.getPassword()));
        } catch (DisabledException e) {
            logger.error(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        } catch (BadCredentialsException e) {
            logger.error(e.getMessage());
            throw new BadCredentialsException("INVALID_CREDENTIALS");
        } 
         UserDetails userDetails = userProvider.loadUserByUsername(userMaxsus.getUsername());
         String token = jwtTokenUtil.generateToken(userDetails, userMaxsus.isRememberMe());
        return ResponseEntity.ok(new Token(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserVM userVM) throws Exception{
        userService.register(userVM);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<?> editAccount(@RequestBody UserVM userVM) throws Exception {
        userService.update(userVM);
        return null;
    }

    @GetMapping()
    public ResponseEntity<UserDTO> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}













