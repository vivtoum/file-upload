package com.yt.file.upload.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;


/**
 * 公共配置 BA
 */

@Slf4j
@ComponentScan({"com.yt.*"})
@EnableJpaRepositories(basePackages = {"com.yt.*"}, repositoryBaseClass = SimpleJpaRepository.class)
@EnableScheduling
@RestController
@Configuration
public class ApplicationConfig {

    @Autowired
    private Environment env;

    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> log.info("\n启动成功：http://localhost:" + env.getProperty("server.port"));
    }

}
