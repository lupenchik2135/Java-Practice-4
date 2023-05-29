package com.example.fourthpractice.service;

import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.messages.requests.UserLoginRequest;
import com.example.fourthpractice.messages.requests.UserRegisterRequest;
import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.models.UserModel;
import com.example.fourthpractice.models.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public TokenModel register(UserRegisterRequest registerRequest) {

        UserEntity newUser = userDao.createUser(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                UserRole.USER
        );


        String jwt = jwtService.generateToken(UserModel.fromEntity(newUser));

        return new TokenModel(
                newUser.getEmail(),
                jwt,
                newUser.getUserId()
        );
    }

    @Override
    public TokenModel login(UserLoginRequest loginRequest) {
        if(!userDao.isUserExist(loginRequest.getEmail())){
            log.info("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), userDao.getPasswordHash(loginRequest.getEmail()))){
            throw new UsernameNotFoundException("Password not match");
        }

        UserModel user = UserModel.fromEntity(userDao.getUserByEmail(loginRequest.getEmail()));

        return new TokenModel(user.getEmail(), jwtService.generateToken(user), null);
    }
}