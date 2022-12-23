package com.shashashark.amugeona.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicInsert
public class Article extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleSeq;
    @Column(name = "user_seq")
    private Long userSeq;
    private Long boardSeq;
    private String title;
    private String content;
    private Long articleLike;
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

    public void updateLike(Long articleLike) {
        this.articleLike = articleLike;
    }

    public void updateViewCnt() {
        this.viewCnt++;
    }
}
