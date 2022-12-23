package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Article extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleSeq;
    @Column(name = "user_seq")
    private Long userSeq;
    private Long boardSeq;
    private String title;
    private String content;
    private Long like;
    private Long viewCnt;
    private String info;

    @ManyToOne
    @JoinColumn(name = "user_seq", insertable = false, updatable = false)
    private User user;

    public void modify(String title, String content, String info) {
        this.title = title;
        this.content = content;
        this.info = info;
    }

    public void updateLike(Long like) {
        this.like = like;
    }

    public void updateViewCnt() {
        this.viewCnt++;
    }
}
