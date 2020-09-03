package com.smida.test.task.smida.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smida.test.task.smida.domain.Share;
import com.smida.test.task.smida.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(name = "jsonEnricher", havingValue = "true")
public class JsonEnricher implements CommandLineRunner {

    private final ShareService shareService;

    @Value("classpath:data.json")
    private Resource resource;

    @Autowired
    public JsonEnricher(ShareService shareService) {
        this.shareService = shareService;
    }

    @Override
    public void run(String... args) throws Exception {
        new ObjectMapper().readValue(resource.getFile(), new TypeReference<List<Share>>() {
        })
                .forEach(shareService::create);
    }
}
