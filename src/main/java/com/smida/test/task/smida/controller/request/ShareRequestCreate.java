package com.smida.test.task.smida.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ShareRequestCreate {

    @NotNull
    @Size(min = 5, max = 200)
    private String comment;

    @Positive
    private int sharesNumber;

    @Positive
    private int erdpou;

    @Positive
    private double nominalValue;

    private Timestamp releaseDate;

    @JsonCreator
    public ShareRequestCreate(
            @JsonProperty("comment") String comment,
            @JsonProperty("shares number") int sharesNumber,
            @JsonProperty("erdpou") int erdpou,
            @JsonProperty("nominal value") double nominalValue,
            @JsonProperty("releaseDate") Timestamp releaseDate) {
        this.comment = comment;
        this.sharesNumber = sharesNumber;
        this.erdpou = erdpou;
        this.nominalValue = nominalValue;
        this.releaseDate = releaseDate;
    }
}

