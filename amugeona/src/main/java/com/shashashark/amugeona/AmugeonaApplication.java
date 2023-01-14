package com.shashashark.amugeona;

import com.shashashark.amugeona.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Import({SwaggerConfig.class, S3Config.class, QueryDslConfig.class, JpaConfig.class, WebConfig.class, SecurityConfig.class})
@SpringBootApplication
public class AmugeonaApplication {

	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(AmugeonaApplication.class, args);
	}

}
