package com.danghouse.cocov1.service;


import com.danghouse.cocov1.dto.JoinDTO;
import com.danghouse.cocov1.entity.UserEntity;
import com.danghouse.cocov1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setEmail(joinDTO.getEmail());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }

    public boolean validateEmailDuplication(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean validateUserNameDuplication(String userName) {
        return userRepository.existsByUsername(userName);
    }




}
