package com.smida.test.task.smida.converter;

import com.smida.test.task.smida.controller.request.ShareRequestCreate;
import com.smida.test.task.smida.utils.UtilOperations;
import com.smida.test.task.smida.domain.Share;

public class ShareRequestCreateToShareConverter {

    public static Share convert(ShareRequestCreate shareRequestCreate) {
        return Share.builder()
                .comment(shareRequestCreate.getComment())
                .nominalValue(shareRequestCreate.getNominalValue())
                .sharesNumber(shareRequestCreate.getSharesNumber())
                .erdpou(shareRequestCreate.getErdpou())
                .releaseDate(shareRequestCreate.getReleaseDate())
                .totalNominalValue(UtilOperations.calculateTotalNominalValue(
                        shareRequestCreate.getNominalValue(),
                        shareRequestCreate.getSharesNumber()
                ))
                .build();
    }
}
