package com.smida.test.task.smida.converter;

import com.smida.test.task.smida.controller.response.ShareResponse;
import com.smida.test.task.smida.domain.Share;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ShareToShareResponseConverterTest {

    @Test
    public void convertShareToShareResponse() {
        ShareResponse expectedShareResponse = prepareExpectedShareResponse();
        ShareResponse actualShareResponse = ShareToShareResponseConverter.convert(prepareShare());

        assertEquals(expectedShareResponse.getId(), actualShareResponse.getId());
        assertEquals(expectedShareResponse.getComment(), actualShareResponse.getComment());
        assertEquals(expectedShareResponse.getErdpou(), actualShareResponse.getErdpou());
        assertEquals(expectedShareResponse.getNominalValue(), actualShareResponse.getNominalValue());
        assertEquals(expectedShareResponse.getReleaseDate(), actualShareResponse.getReleaseDate());
        assertEquals(expectedShareResponse.getSharesNumber(), actualShareResponse.getSharesNumber());
        assertEquals(expectedShareResponse.getTotalNominalValue(), actualShareResponse.getTotalNominalValue());
    }

    private ShareResponse prepareExpectedShareResponse() {
        return ShareResponse.builder()
                .id(123)
                .comment("Mersedes")
                .erdpou(123123)
                .releaseDate(new Timestamp(20001023))
                .sharesNumber(100)
                .nominalValue(200.00)
                .totalNominalValue(20000.00)
                .build();
    }

    private Share prepareShare() {
        return Share.builder()
                .id(123)
                .comment("Mersedes")
                .erdpou(123123)
                .releaseDate(new Timestamp(20001023))
                .sharesNumber(100)
                .nominalValue(200.00)
                .totalNominalValue(20000.00)
                .build();

    }
}