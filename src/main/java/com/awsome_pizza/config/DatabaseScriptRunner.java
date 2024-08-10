package com.awsome_pizza.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


@Component
public class DatabaseScriptRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseScriptRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void runScripts() {
        Resource script = new ClassPathResource("sql/create_trigger.sql");
        try {
            String sql = new String(Files.readAllBytes(script.getFile().toPath()), StandardCharsets.UTF_8);
            jdbcTemplate.execute(sql);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read SQL script", e);
        }
    }
}
