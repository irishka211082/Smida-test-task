package com.smida.test.task.smida.controller.converter;

import com.smida.test.task.smida.controller.response.ShareResponse;
import com.smida.test.task.smida.domain.Share;

public class ShareToShareResponseConverter {

    public static ShareResponse convert(Share share) {
        return ShareResponse.builder()
                .id(share.getId())
                .comment(share.getComment())
                .erdpou(share.getErdpou())
                .nominalValue(share.getNominalValue())
                .sharesNumber(share.getSharesNumber())
                .totalNominalValue(share.getTotalNominalValue())
                .releaseDate(share.getReleaseDate())
                .build();
    }
}
