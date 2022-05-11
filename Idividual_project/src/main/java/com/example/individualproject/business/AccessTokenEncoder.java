package com.example.individualproject.business;


import com.example.individualproject.dto.login.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}
