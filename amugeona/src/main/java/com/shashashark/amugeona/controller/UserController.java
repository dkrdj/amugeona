package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.UserDto;
import com.shashashark.amugeona.model.dto.UserUpdateParam;
import com.shashashark.amugeona.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final UserService userService;

    //id를 통해 비밀번호 찾기
    @GetMapping("/user")
    public ResponseEntity<String> getUser(String userId, String email) {
        if (userService.getUser(userId).orElseThrow().getEmail().equals(email)) {
            String pwd = userService.getUser(userId).orElseThrow().getPassword();
            return new ResponseEntity<>(pwd, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(FAIL, HttpStatus.OK);
        }
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        //아이디 & 닉네임 중복검사 후 true로 오면 이미 존재하는 것이므로 fail
        if (userService.checkUser(userDto.getUserId(), userDto.getNickname()))
            return new ResponseEntity<>(FAIL, HttpStatus.OK);

        userService.addUser(userDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    //회원정보 수정
    @PutMapping("/user")
    public ResponseEntity<String> modifyUser(@RequestBody UserUpdateParam param) {
        userService.modifyUser(param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
