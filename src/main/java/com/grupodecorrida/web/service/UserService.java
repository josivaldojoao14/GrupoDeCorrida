package com.grupodecorrida.web.service;

import com.grupodecorrida.web.dto.RegistrationDto;
import com.grupodecorrida.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
