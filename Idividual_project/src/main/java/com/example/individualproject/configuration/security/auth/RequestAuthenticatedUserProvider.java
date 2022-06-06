package com.example.individualproject.configuration.security.auth;

import com.example.individualproject.dto.login.AccessTokenDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class RequestAuthenticatedUserProvider {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccessTokenDTO getAuthenticatedUserInRequest() {
        final SecurityContext context = SecurityContextHolder.getContext();

        //TODO SecurityContextHolder.getContext() is never null why check if it is null, ask teacher for assignment about csrf.

        if (context == null) {
            return null;
        }

        final Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        final Object details = authentication.getDetails();
        if (!(details instanceof AccessTokenDTO)) {
            return null;
        }

        return (AccessTokenDTO) authentication.getDetails();
    }
}
