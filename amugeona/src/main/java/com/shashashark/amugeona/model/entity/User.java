package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
    private String id;
    private String password;
    private String email;
    private Integer age;
    private String name;
    private String nickname;
    private String profile_img;

    public void updateUser(String password, String email, String nickname, String profile_img) {
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.profile_img = profile_img;
    }
}
