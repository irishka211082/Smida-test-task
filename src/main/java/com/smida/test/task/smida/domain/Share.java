package com.smida.test.task.smida.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "shares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "shares_number", nullable = false)
    private int sharesNumber;

    @Column(name = "erdpou", nullable = false)
    private int erdpou;

    @Column(name = "total_nominal_value", nullable = false)
    private BigDecimal totalNominalValue;

    @Column(name = "nominal_value", nullable = false)
    private BigDecimal nominalValue;

    @CreationTimestamp
    @Column(name = "release_date")
    private Timestamp releaseDate;

    @Column(name = "status_is_active")
    private int isActive;
}
