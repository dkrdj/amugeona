package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.UserDto;
import com.shashashark.amugeona.model.entity.User;
import com.shashashark.amugeona.model.repository.UserRepository;
import com.shashashark.amugeona.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public Optional<UserDto> getUser(String id) {
        return Optional.ofNullable(toDto(userRepository.findById(id).orElseThrow()));
    }

    @Override
    public void addUser(UserDto userDto) {
        userRepository.save(toEntity(userDto));
    }

    @Override
    public void modifyUser(UserDto userDto) {
        //유저 불러와서 수정후 다시 집어넣기
        User originUser = userRepository.findById(userDto.getId()).orElseThrow();
        originUser.updateUser(userDto.getPassword(), userDto.getEmail(), userDto.getNickname(), userDto.getProfile_img());
    }
}
