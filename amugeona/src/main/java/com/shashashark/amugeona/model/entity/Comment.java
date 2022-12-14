package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentSeq;

    private Long articleSeq;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    private String content;

    public void modify(String content) {
        this.content = content;
    }
}
