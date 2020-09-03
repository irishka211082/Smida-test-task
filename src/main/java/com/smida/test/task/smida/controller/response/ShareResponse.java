package com.smida.test.task.smida.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShareResponse {

    private long id;
    private String comment;
    private int sharesNumber;
    private int erdpou;
    private BigDecimal totalNominalValue;
    private double nominalValue;
    private Timestamp releaseDate;
}
