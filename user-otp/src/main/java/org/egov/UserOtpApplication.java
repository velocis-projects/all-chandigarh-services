package org.egov;

import org.egov.tracer.config.TracerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(TracerConfiguration.class)
public class UserOtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserOtpApplication.class, args);
	}

}
