package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.UserDto;
import com.shashashark.amugeona.model.param.UserInfo;
import com.shashashark.amugeona.model.param.UserUpdateParam;
import com.shashashark.amugeona.model.service.UserService;
import com.shashashark.amugeona.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private static final String HEADER_AUTH = "access-token";
    private static final String MESSAGE = "msg";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final UserService userService;
    private final JwtUtil jwtUtil;

    //id를 통해 비밀번호 찾기
    @GetMapping("/find")
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
    @PutMapping("/modify")
    public ResponseEntity<String> modifyUser(@RequestBody UserUpdateParam param) {
        userService.modifyUser(param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(String id, String password) {
        UserDto user = userService.getUser(id).orElseThrow();

        HashMap<String, Object> result = new HashMap<>();
        HttpStatus status;

        try {
            if (user.getPassword().equals(password)) {
                UserInfo token = UserInfo.builder()
                        .userSeq(user.getUserSeq())
                        .userId(user.getUserId())
                        .name(user.getName())
                        .nickname(user.getNickname())
                        .profileImg(user.getProfileImg())
                        .build();
                result.put(HEADER_AUTH, jwtUtil.createToken(token));
                result.put(MESSAGE, SUCCESS);
            } else {
                result.put(MESSAGE, FAIL);
            }
            status = HttpStatus.ACCEPTED;
        } catch (UnsupportedEncodingException e) {
            result.put(MESSAGE, FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }
}
