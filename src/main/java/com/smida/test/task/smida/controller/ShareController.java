package com.smida.test.task.smida.controller;

import com.smida.test.task.smida.controller.request.ShareRequestCreate;
import com.smida.test.task.smida.controller.request.ShareRequestUpdate;
import com.smida.test.task.smida.controller.response.ShareResponse;
import com.smida.test.task.smida.converter.ShareRequestCreateToShareConverter;
import com.smida.test.task.smida.converter.ShareRequestUpdateToShareConverter;
import com.smida.test.task.smida.converter.ShareToShareResponseConverter;
import com.smida.test.task.smida.domain.Status;
import com.smida.test.task.smida.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/share", produces = "application/json", consumes = "application/json")
public class ShareController {

    private final ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping
    public ResponseEntity<List<ShareResponse>> getShares() {
        return new ResponseEntity<>(
                shareService.getAllShares().stream()
                        .map(ShareToShareResponseConverter::convert)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("{erdpou}")
    public ResponseEntity<List<ShareResponse>> getSharesByErdpou(@PathVariable @Positive int erdpou) {
        return new ResponseEntity<>(
                shareService.getAllShares(erdpou).stream()
                        .map(ShareToShareResponseConverter::convert)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("{status}")
    public ResponseEntity<List<ShareResponse>> getSharesByStatus(@PathVariable Status status) {
        return new ResponseEntity<>(
                shareService.getAllShares(status).stream()
                        .map(ShareToShareResponseConverter::convert)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareResponse> getShare(@PathVariable @Positive Long id) {
        return new ResponseEntity<>(ShareToShareResponseConverter.convert(shareService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShareResponse> createShare(@RequestBody @Valid ShareRequestCreate shareRequestCreate) {
        return new ResponseEntity<>(
                ShareToShareResponseConverter.convert(shareService.create(ShareRequestCreateToShareConverter.convert(shareRequestCreate))),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShareResponse> editShare(
            @PathVariable @Positive Long id,
            @RequestBody @Valid ShareRequestUpdate shareRequestUpdate) {
        return new ResponseEntity<>(
                ShareToShareResponseConverter.convert(
                        shareService.update(
                                id,
                                ShareRequestUpdateToShareConverter.convert(shareRequestUpdate))),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShareResponse> deleteShare(@PathVariable @Positive Long id) {
        return new ResponseEntity<>(ShareToShareResponseConverter.convert(shareService.delete(id)), HttpStatus.OK);
    }
}
