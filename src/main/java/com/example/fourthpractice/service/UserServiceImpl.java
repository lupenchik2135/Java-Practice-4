package com.example.fourthpractice.service;

import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.messages.requests.UserLoginRequest;
import com.example.fourthpractice.messages.requests.UserRegisterRequest;
import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.models.UserModel;
import com.example.fourthpractice.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final HttpServletRequest request;

    @Override
    public TokenModel register(UserRegisterRequest registerRequest) {
        if (userDao.getUserByEmail(registerRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }

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
        if (!userDao.isUserExist(loginRequest.getEmail())) {
            log.info("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDao.getPasswordHash(loginRequest.getEmail()))) {
            throw new UsernameNotFoundException("Password not match");
        }

        UserModel user = UserModel.fromEntity(userDao.getUserByEmail(loginRequest.getEmail()));

        return new TokenModel(user.getEmail(), jwtService.generateToken(user), null);
    }
    @Override
    public String deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        UserEntity user = userDao.getUserByEmail(authentication.getName());

        userDao.deleteById(user.getUserId());
        String message = "Пользователь полностью удалён";
        return message;
    }

    private String getTokenFromHeader() {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(header);
        if (ObjectUtils.isEmpty(header)) {
            return null;
        }
        return header;
    }
}