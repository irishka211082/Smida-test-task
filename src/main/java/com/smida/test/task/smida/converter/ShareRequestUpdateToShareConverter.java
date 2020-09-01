package com.smida.test.task.smida.converter;

import com.smida.test.task.smida.controller.request.ShareRequestUpdate;
import com.smida.test.task.smida.utils.UtilOperations;
import com.smida.test.task.smida.domain.Share;

public class ShareRequestUpdateToShareConverter {

    public static Share convert(ShareRequestUpdate shareRequestUpdate) {
        return Share.builder()
                .comment(shareRequestUpdate.getComment())
                .nominalValue(shareRequestUpdate.getNominalValue())
                .sharesNumber(shareRequestUpdate.getSharesNumber())
                .releaseDate(shareRequestUpdate.getReleaseDate())
                .totalNominalValue(UtilOperations.calculateTotalNominalValue(
                        shareRequestUpdate.getNominalValue(),
                        shareRequestUpdate.getSharesNumber()
                ))
                .build();
    }
}
