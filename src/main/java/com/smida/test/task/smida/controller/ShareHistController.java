package com.smida.test.task.smida.controller;

import com.smida.test.task.smida.domain.ShareHist;
import com.smida.test.task.smida.service.ShareHistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/share/hist", produces = "application/json", consumes = "application/json")
public class ShareHistController {

    private final ShareHistService shareHistService;

    @Autowired
    public ShareHistController(ShareHistService shareHistService) {
        this.shareHistService = shareHistService;
    }

    @GetMapping
    public ResponseEntity<List<ShareHist>> getAllHistory() {
        return new ResponseEntity<>(
                new ArrayList<>(shareHistService.getAllHists()),
                HttpStatus.OK);
    }

    @GetMapping("/{erdpou}")
    public ResponseEntity<List<ShareHist>> getAllHistoryByErdpou(@PathVariable @Positive int erdpou) {
        return new ResponseEntity<>(
                new ArrayList<>(shareHistService.getAllHistsByErdpou(erdpou)),
                HttpStatus.OK);
    }
}
