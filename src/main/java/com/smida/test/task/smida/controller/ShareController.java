package com.smida.test.task.smida.controller;

import com.smida.test.task.smida.service.impl.ShareServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/v1")
public class ShareController {

    @Autowired
    private ShareServiceImpl shareService;

}
