package com.smida.test.task.smida.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareRequest {
    private String comment;
    private int sharesNumber;
    private int erdpou;
    private double nominalValue;
    private Timestamp releaseDate;
}
