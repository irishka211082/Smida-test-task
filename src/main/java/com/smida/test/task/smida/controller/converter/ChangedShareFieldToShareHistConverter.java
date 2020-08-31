package com.smida.test.task.smida.controller.converter;

import com.smida.test.task.smida.domain.ChangedShareField;
import com.smida.test.task.smida.domain.ShareHist;

public class ChangedShareFieldToShareHistConverter {

    public static ShareHist convert(ChangedShareField changedShareField) {
        return ShareHist.builder()
                .name(changedShareField.getFieldName().name())
                .oldValue(changedShareField.getNewValue().toString())
                .newValue(changedShareField.getOldValue().toString())
                .build();
    }
}
