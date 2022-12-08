package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userSeq;
    private String userId;
    private String password;
    private String email;
    private Integer age;
    private String name;
    private String nickname;
    private String profileImg;
}
