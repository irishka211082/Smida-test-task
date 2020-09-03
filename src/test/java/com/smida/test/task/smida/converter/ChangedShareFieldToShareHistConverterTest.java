package com.smida.test.task.smida.converter;

import com.smida.test.task.smida.domain.ChangedShareField;
import com.smida.test.task.smida.domain.FieldName;
import com.smida.test.task.smida.domain.ShareHist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangedShareFieldToShareHistConverterTest {

    @Test
    public void convertShareFieldToShareHist() {
       ShareHist expectedHist = prepareExpectedShareHist();
       ShareHist actualHist = ChangedShareFieldToShareHistConverter.convert(prepareChangedShareField());

       assertEquals(expectedHist.getName(), actualHist.getName());
       assertEquals(expectedHist.getOldValue(), actualHist.getOldValue());
       assertEquals(expectedHist.getNewValue(), actualHist.getNewValue());
       assertEquals(expectedHist.getErdpou(), actualHist.getErdpou());
    }

    private ChangedShareField prepareChangedShareField() {
        return ChangedShareField.builder()
                .fieldName(FieldName.COMMENT)
                .oldValue("TNK-oil")
                .newValue("Rosneft-oil")
                .erdpou(45454545)
                .build();
    }

    private ShareHist prepareExpectedShareHist() {
        return ShareHist.builder()
                .name("COMMENT")
                .oldValue("TNK-oil")
                .newValue("Rosneft-oil")
                .erdpou(45454545)
                .build();
    }
}