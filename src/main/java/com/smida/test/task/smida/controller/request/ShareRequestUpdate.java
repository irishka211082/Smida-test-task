package com.smida.test.task.smida.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareRequestUpdate {

    @NotNull
    @Size(min = 5, max = 200)
    private String comment;

    @Positive
    private int sharesNumber;

    @Positive
    private double nominalValue;

    @NotNull
    private Timestamp releaseDate;
}
