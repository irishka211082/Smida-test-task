package com.smida.test.task.smida.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangedShareField {

    private ShareChangedFieldName fieldName;
    private Object oldValue;
    private Object newValue;
    private int erdpou;
}
