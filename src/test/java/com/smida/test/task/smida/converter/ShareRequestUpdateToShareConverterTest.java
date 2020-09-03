package com.smida.test.task.smida.converter;

import com.smida.test.task.smida.controller.request.ShareRequestCreate;
import com.smida.test.task.smida.domain.Share;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ShareRequestUpdateToShareConverterTest {

    @Test
    public void convertShareRequestUpdateToShare() {
        Share expectedShare = prepareExpectedShare();
        Share actualShare = ShareRequestCreateToShareConverter.convert(prepareShareRequestUpdate());

        assertEquals(expectedShare.getComment(), actualShare.getComment());
        assertEquals(expectedShare.getErdpou(), actualShare.getErdpou());
        assertEquals(expectedShare.getNominalValue(), actualShare.getNominalValue());
        assertEquals(expectedShare.getReleaseDate(), actualShare.getReleaseDate());
        assertEquals(expectedShare.getSharesNumber(), actualShare.getSharesNumber());
    }

    private ShareRequestCreate prepareShareRequestUpdate() {
        return ShareRequestCreate.builder()
                .comment("Amazon")
                .erdpou(5675678)
                .nominalValue(100.56)
                .sharesNumber(34564)
                .releaseDate(new Timestamp(20000115))
                .build();
    }

    private Share prepareExpectedShare() {
        return Share.builder()
                .comment("Amazon")
                .erdpou(5675678)
                .nominalValue(100.56)
                .sharesNumber(34564)
                .releaseDate(new Timestamp(20000115))
                .build();
    }


}