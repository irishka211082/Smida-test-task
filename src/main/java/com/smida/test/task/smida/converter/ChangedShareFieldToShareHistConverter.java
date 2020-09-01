package com.smida.test.task.smida.converter;

import com.smida.test.task.smida.domain.ChangedShareField;
import com.smida.test.task.smida.domain.ShareHist;

public class ChangedShareFieldToShareHistConverter {

    public static ShareHist convert(ChangedShareField changedShareField) {
        return ShareHist.builder()
                .erdpou(changedShareField.getErdpou())
                .name(changedShareField.getFieldName().name())
                .oldValue(changedShareField.getNewValue().toString())
                .newValue(changedShareField.getOldValue().toString())
                .build();
    }
}
