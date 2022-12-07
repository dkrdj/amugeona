package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.JwtUser;
import com.shashashark.amugeona.model.dto.UserDto;
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
public class UserController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final UserService userService;
    private final JwtUtil jwtUtil;

    //id를 통해 비밀번호 찾기
    @PostMapping("/findPwd")
    public ResponseEntity<String> getUser(String id) {
        String pwd = userService.getUser(id).orElseThrow().getPassword();
        return new ResponseEntity<>(pwd, HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    //회원정보 수정
    @PutMapping("/modify")
    public ResponseEntity<String> modifyUser(@RequestBody UserDto userDto) {
        userService.modifyUser(userDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(String id, String password) {
        UserDto user = userService.getUser(id).orElseThrow();

        HashMap<String, Object> result = new HashMap<>();
        HttpStatus status = null;

        try {
            if (user.getPassword().equals(password)) {
                JwtUser token = JwtUser.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .nickname(user.getNickname())
                        .profile_img(user.getProfile_img())
                        .build();
                result.put("access-token", jwtUtil.createToken("loginUser", token));
                result.put("msg", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
        } catch (UnsupportedEncodingException e) {
            result.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(result, status);
    }

//    //마이페이지에 나타낼 유저 정보
//    @GetMapping("/myPage")
//    public ResponseEntity<JwtUser> detailUser(HttpServletRequest request) {
//
//        try {
//            String token = request.getHeader(HEADER_AUTH);
//            JwtUser loginUser = jwtUtil.getToken(token);
//
//            return new ResponseEntity<>(loginUser, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
//        }
//
//    }
}
