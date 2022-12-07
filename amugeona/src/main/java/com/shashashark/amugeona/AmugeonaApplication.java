package com.shashashark.amugeona;

import com.shashashark.amugeona.config.JpaConfig;
import com.shashashark.amugeona.config.QueryDslConfig;
import com.shashashark.amugeona.config.S3Config;
import com.shashashark.amugeona.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({SwaggerConfig.class, S3Config.class, QueryDslConfig.class, JpaConfig.class})
@SpringBootApplication
public class AmugeonaApplication {

	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(AmugeonaApplication.class, args);
	}

}
