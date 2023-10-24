package com.grupodecorrida.web.service.impl;

import com.grupodecorrida.web.dto.RegistrationDto;
import com.grupodecorrida.web.models.Role;
import com.grupodecorrida.web.models.UserEntity;
import com.grupodecorrida.web.repository.RoleRepository;
import com.grupodecorrida.web.repository.UserRepository;
import com.grupodecorrida.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
