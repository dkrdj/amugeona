package com.shashashark.amugeona.config;

import com.shashashark.amugeona.model.repository.*;
import com.shashashark.amugeona.model.service.*;
import com.shashashark.amugeona.model.service.Impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final ReplyCommentRepository replyCommentRepository;
    private final StarRepository starRepository;
    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleServiceImpl(articleRepository);
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository);
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentRepository);
    }

    @Bean
    public ReplyCommentService replyCommentService() {
        return new ReplyCommentServiceImpl(replyCommentRepository);
    }

    @Bean
    public StarService starService() {
        return new StarServiceImpl(starRepository, articleRepository);
    }
}