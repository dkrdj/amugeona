package com.shashashark.amugeona.config;

import com.shashashark.amugeona.model.repository.ArticleRepository;
import com.shashashark.amugeona.model.repository.BoardRepository;
import com.shashashark.amugeona.model.repository.CommentRepository;
import com.shashashark.amugeona.model.repository.UserRepository;
import com.shashashark.amugeona.model.service.ArticleService;
import com.shashashark.amugeona.model.service.BoardService;
import com.shashashark.amugeona.model.service.CommentService;
import com.shashashark.amugeona.model.service.Impl.ArticleServiceImpl;
import com.shashashark.amugeona.model.service.Impl.BoardServiceImpl;
import com.shashashark.amugeona.model.service.Impl.CommentServiceImpl;
import com.shashashark.amugeona.model.service.Impl.UserServiceImpl;
import com.shashashark.amugeona.model.service.UserService;
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
}
