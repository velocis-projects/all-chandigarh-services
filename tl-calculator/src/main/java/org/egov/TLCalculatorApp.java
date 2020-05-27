package org.egov;


import org.egov.tracer.config.TracerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@Import({ TracerConfiguration.class })
public class TLCalculatorApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TLCalculatorApp.class, args);
    }

}
