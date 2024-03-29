package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.UserDto;
import com.shashashark.amugeona.model.dto.UserUpdateParam;
import com.shashashark.amugeona.model.entity.User;

import java.util.Optional;

public interface UserService {

    //비밀번호 찾기에 사용하기 위한 id로 유저 찾기
    Optional<UserDto> getUser(String userId);

    boolean checkUser(String userId, String nickname);

    //회원가입에 사용
    void addUser(UserDto userDto);

    //회원 정보 수정에 사용
    void modifyUser(UserUpdateParam param);

    // DB에 저장할 때
    default User toEntity(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .name(userDto.getName())
                .nickname(userDto.getNickname())
                .profile_img(userDto.getProfileImg())
                .role(userDto.getRole())
                .build();
    }

    //DB에서 불러올때
    default UserDto toDto(User user) {
        return UserDto.builder()
                .userSeq(user.getUserSeq())
                .userId(user.getUserId())
                .password(user.getPassword())
                .email(user.getEmail())
                .age(user.getAge())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileImg(user.getProfile_img())
                .role(user.getRole())
                .build();
    }


}
