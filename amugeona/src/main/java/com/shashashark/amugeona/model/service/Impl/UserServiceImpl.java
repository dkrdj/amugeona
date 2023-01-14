package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.UserDto;
import com.shashashark.amugeona.model.dto.UserUpdateParam;
import com.shashashark.amugeona.model.entity.User;
import com.shashashark.amugeona.model.repository.UserRepository;
import com.shashashark.amugeona.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> getUser(String userId) {
        return Optional.ofNullable(toDto(userRepository.findByUserId(userId).orElseThrow()));
    }

    @Override
    public boolean checkUser(String userId, String nickname) {
        return userRepository.findByUserIdOrNickname(userId, nickname).isPresent();
    }


    @Override
    public void addUser(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setRole("ROLE_USER");
        userRepository.save(toEntity(userDto));
    }

    @Override
    @Transactional
    public void modifyUser(UserUpdateParam param) {
        //유저 불러와서 수정후 다시 집어넣기
        User originUser = userRepository.findById(param.getUserSeq()).orElseThrow();
        originUser.updateUser(param.getPassword(), param.getEmail(), param.getNickname(), param.getProfileImg());
    }

}
