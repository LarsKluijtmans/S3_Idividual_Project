package com.example.individualproject.business.impl;

import com.example.individualproject.business.AccessTokenEncoder;
import com.example.individualproject.business.exception.InvalidCredentialsException;
import com.example.individualproject.configuration.security.PasswordEncoderConfig;
import com.example.individualproject.dto.login.LoginRequestDTO;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class LoginUseCaseImplTest {

    @Mock
    private NormalUserRepository userRepository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private PasswordEncoder passwordEncoder = new PasswordEncoderConfig().createBCryptPasswordEncoder();
    @Mock
    private AccessTokenEncoder accessTokenEncoder;
    @InjectMocks
    private LoginUseCaseImpl  loginUseCaseImpl;


    @Test
    void login_NormalUserFound_PassNotMatched() {
        NormalUser user = new NormalUser(1l,"Lars","Lars","Lars","Lars","Lars","Lars");

        when(adminRepository.findByUsername("Lars"))
                .thenReturn(null);
        when(userRepository.findByUsername("Lars"))
                .thenReturn(user);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(new LoginRequestDTO("Lars","Lars")));

        verify(adminRepository).findByUsername("Lars");
        verify(userRepository).findByUsername("Lars");
    }
    @Test
    void login_AdminFound_PassNotMatched() {
        Admin admin = new Admin(1l,"Lars","Lars");

        when(adminRepository.findByUsername("Lars"))
                .thenReturn(admin);
        when(userRepository.findByUsername("Lars"))
                .thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(new LoginRequestDTO("Lars","Lars")));

        verify(adminRepository).findByUsername("Lars");
        verify(userRepository).findByUsername("Lars");
    }

    @Test
    void login_NothingFound() {
        when(adminRepository.findByUsername("thing"))
                .thenReturn(null);
        when(userRepository.findByUsername("thing"))
                .thenReturn(null);

        LoginRequestDTO result =   new LoginRequestDTO("thing","thing");
        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(result));

        verify(adminRepository).findByUsername("thing");
        verify(userRepository).findByUsername("thing");
    }

    @Test
    void login_NormalUserFound_Pass() {
        passwordEncoder = new PasswordEncoderConfig().createBCryptPasswordEncoder();

        NormalUser user = new NormalUser(1l,"Lars", passwordEncoder.encode("Lars"),"Lars","Lars","Lars","Lars");

        when(adminRepository.findByUsername("Lars"))
                .thenReturn(null);
        when(userRepository.findByUsername("Lars"))
                .thenReturn(user);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(new LoginRequestDTO("Lars","Lars")));

        verify(adminRepository).findByUsername("Lars");
        verify(userRepository).findByUsername("Lars");
    }
    @Test
    void login_AdminFound_Pass() {
        passwordEncoder  = new PasswordEncoderConfig().createBCryptPasswordEncoder();

        Admin admin = new Admin(1l,"Lars", passwordEncoder.encode("Lars"));

        when(adminRepository.findByUsername("Lars"))
                .thenReturn(admin);
        when(userRepository.findByUsername("Lars"))
                .thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(new LoginRequestDTO("Lars","Lars")));

        verify(adminRepository).findByUsername("Lars");
        verify(userRepository).findByUsername("Lars");
    }

}