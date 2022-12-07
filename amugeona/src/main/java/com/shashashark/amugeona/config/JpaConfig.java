package com.shashashark.amugeona.config;

import com.shashashark.amugeona.model.repository.UserRepository;
import com.shashashark.amugeona.model.service.Impl.UserServiceImpl;
import com.shashashark.amugeona.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
    private final UserRepository userRepository;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository);
    }
}
