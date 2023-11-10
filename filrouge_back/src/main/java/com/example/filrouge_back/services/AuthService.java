package com.example.filrouge_back.services;

import com.example.filrouge_back.components.JwtTokenGenerator;
import com.example.filrouge_back.entities.Role;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.authdtos.AuthRequest;
import com.example.filrouge_back.models.enums.RoleName;
import com.example.filrouge_back.repositories.RoleRepository;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator tokenGenerator;

    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public String authenticate(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getMail(),
                authRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenGenerator.generateToken(authentication);
    }

    public void signOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public String register(AuthRequest authRequest) {
        if (!userEntityRepository.existsByMail(authRequest.getMail())) {
            Role role = roleRepository
                    .findByRoleName(RoleName.USER)
                    .orElseGet(() -> roleRepository.save(Role.builder().roleName(RoleName.USER).build()));

            UserEntity newUser = userMapper.authRequestToNewUserEntity(authRequest);
            newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            newUser.setRole(role);

            userEntityRepository.save(newUser);
        }

        return authenticate(authRequest);
    }
}
