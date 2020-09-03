package com.smida.test.task.smida.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "erdpou", nullable = false)
    private int erdpou;

    @Column(name = "field_name")
    private String name;

    @Column(name = "old_value", nullable = false)
    private String oldValue;

    @Column(name = "new_value", nullable = false)
    private String newValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShareHist)) return false;
        ShareHist shareHist = (ShareHist) o;
        return id == shareHist.id &&
                erdpou == shareHist.erdpou &&
                Objects.equals(name, shareHist.name) &&
                Objects.equals(oldValue, shareHist.oldValue) &&
                Objects.equals(newValue, shareHist.newValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, erdpou, name, oldValue, newValue);
    }
}
