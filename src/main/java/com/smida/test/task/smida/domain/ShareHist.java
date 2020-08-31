package com.smida.test.task.smida.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shares_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "field_name")
    private String name;

    @Column(name = "old_value", nullable = false)
    private String oldValue;

    @Column(name = "new_value", nullable = false)
    private String newValue;
}
