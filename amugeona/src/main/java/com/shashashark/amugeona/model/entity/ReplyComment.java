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
public class ReplyComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replySeq;

    private Long rootSeq;
    private Long userSeq;

    @ManyToOne
    private User user;

    private String content;

    public void modify(String content) {
        this.content = content;
    }

}
