package com.example.filrouge_back.services;

import com.example.filrouge_back.components.JwtTokenGenerator;
import com.example.filrouge_back.entities.Role;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.DuplicateUserMailException;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.authdtos.AuthRequest;
import com.example.filrouge_back.models.authdtos.AuthResponse;
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

    public AuthResponse authenticate(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getMail(),
                authRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenGenerator.generateToken(authentication);

        return AuthResponse.builder()
                .token(token)
                .user(userMapper.userToUserDisplayDto(findUserByMail(authRequest.getMail())))
                .build();
    }

    public void signOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public AuthResponse register(AuthRequest authRequest) {
        if (!userEntityRepository.existsByMail(authRequest.getMail())) {
            Role role = roleRepository
                    .findByRoleName(RoleName.USER)
                    .orElseGet(() -> roleRepository.save(Role.builder().roleName(RoleName.USER).build()));

            UserEntity newUser = userMapper.authRequestToNewUserEntity(authRequest);
            newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            newUser.setRole(role);

            userEntityRepository.save(newUser);
        } else {
            throw new DuplicateUserMailException("You already have an account");
        }

        return authenticate(authRequest);
    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    public boolean changeUserPassword(UserEntity user, String password) {
        String currentUserName = getCurrentUserName();
        if (currentUserName.equals(user.getMail())) {
            user.setPassword(passwordEncoder.encode(password));
            userEntityRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public UserEntity findUserByMail(String userMail) {
        return userEntityRepository.findByMail(userMail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with mail " + userMail));
    }
}
