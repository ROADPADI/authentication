package com.roadpadi_authentication.signup_signin.controller;

import com.roadpadi_authentication.signup_signin.model.Role;
import com.roadpadi_authentication.signup_signin.model.RoleEnum;
import com.roadpadi_authentication.signup_signin.model.User;
import com.roadpadi_authentication.signup_signin.payload.request.LoginRequest;
import com.roadpadi_authentication.signup_signin.payload.request.SignupRequest;
import com.roadpadi_authentication.signup_signin.payload.response.JwtResponse;
import com.roadpadi_authentication.signup_signin.payload.response.MessageResponse;
import com.roadpadi_authentication.signup_signin.repository.RoleRepository;
import com.roadpadi_authentication.signup_signin.repository.UserRepository;
import com.roadpadi_authentication.signup_signin.security.jwt.JwtUtils;
import com.roadpadi_authentication.signup_signin.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken, try something different!"));
        }

        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already existing in our system, try to login instead!"));
        }

        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> stringRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(stringRoles == null){
            Role userRole = roleRepository.findByRoleName(RoleEnum.ROLE_CUSTOMER).
                    orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else {
            stringRoles.forEach(role -> {
                switch (role){
                    case "transportCompanyMainAdmin":
                        Role transportCompanyMainAdminRole = roleRepository.findByRoleName(RoleEnum.ROLE_TRANSPORT_COMPANY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(transportCompanyMainAdminRole);

                        break;

                    case "transportCompanyTerminalManager":
                        Role transportCompanyTerminalManagerRole = roleRepository.findByRoleName(RoleEnum.ROLE_TRANSPORT_COMPANY_TERMINAL_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(transportCompanyTerminalManagerRole);

                        break;

                    case "transportCompanyBookingAdmin":
                        Role transportCompanyBookingAdminRole = roleRepository.findByRoleName(RoleEnum.ROLE_TRANSPORT_COMPANY_BOOKING_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(transportCompanyBookingAdminRole);

                        break;

                    case "transportCompanyDriver":
                        Role transportCompanyDriverRole = roleRepository.findByRoleName(RoleEnum.ROLE_TRANSPORT_COMPANY_DRIVER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(transportCompanyDriverRole);

                        break;

                    case "otherCompany":
                        Role otherCompanyRole = roleRepository.findByRoleName(RoleEnum.ROLE_OTHER_COMPANY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(otherCompanyRole);

                        break;

                    case "customerHelper":
                        Role customerHelperRole = roleRepository.findByRoleName(RoleEnum.ROLE_CUSTOMER_HELPER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(customerHelperRole);

                        break;

                    case "fuelStationMainAdmin":
                        Role fuelStationMainRole = roleRepository.findByRoleName(RoleEnum.ROLE_FUEL_STATION)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(fuelStationMainRole);

                        break;

                    case "fuelStationManager":
                        Role fuelStationManagerRole = roleRepository.findByRoleName(RoleEnum.ROLE_FUEL_STATION_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(fuelStationManagerRole);

                        break;

                    case "fuelStationAttendant":
                        Role fuelStationAttendantRole = roleRepository.findByRoleName(RoleEnum.ROLE_FUEL_STATION_ATTENDANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(fuelStationAttendantRole);

                        break;

                    case "roadpadiSuperAdmin":
                        Role roadpadiSuperAdminRole = roleRepository.findByRoleName(RoleEnum.ROLE_SUPER_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(roadpadiSuperAdminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByRoleName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));

    }


}
