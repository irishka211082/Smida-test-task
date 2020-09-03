package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.converter.ChangedShareFieldToShareHistConverter;
import com.smida.test.task.smida.domain.ChangedShareField;
import com.smida.test.task.smida.domain.ChangedShareFields;
import com.smida.test.task.smida.domain.FieldName;
import com.smida.test.task.smida.domain.ShareHist;
import com.smida.test.task.smida.exceptions.NoHistsException;
import com.smida.test.task.smida.repository.HistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShareHistServiceImplTest {

    @InjectMocks
    private ShareHistServiceImpl shareHistService;

    @Mock
    private HistRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addHistory() {
        List<ShareHist> expectedHist = prepareShareHists();
        when(repository.saveAll(prepareShareHists())).thenReturn(expectedHist);

        List<ShareHist> actualHist = shareHistService.addHistory(prepareChangedFields());

        assertNotNull(actualHist);
        assertEquals(expectedHist.size(), actualHist.size());
        assertEquals(expectedHist.get(1).getName(), actualHist.get(1).getName());
    }

    @Test
    public void getAllHists() {
        List<ShareHist> expectedhists = prepareShareHists();
        when(repository.findAll()).thenReturn(expectedhists);

        List<ShareHist> actualHists = shareHistService.getAllHists();

        assertNotNull(actualHists);
        assertEquals(expectedhists.size(), actualHists.size());
    }

    @Test
    public void getAllHistsWhenDbIsEmpty() {
        when(repository.findAll()).thenThrow(new NoHistsException());

        assertThrows(NoHistsException.class, () -> {
            shareHistService.getAllHists();
        });
    }

    @Test
    public void getAllHistsByErdpou() {
        List<ShareHist> expectedhists = prepareShareHists();
        when(repository.findAllByErdpou(10010010)).thenReturn(expectedhists);

        List<ShareHist> actualHists = shareHistService.getAllHistsByErdpou(10010010);

        assertNotNull(actualHists);
        assertEquals(5, actualHists.size());
        assertEquals(10010010, actualHists.get(3).getErdpou());
    }

    @Test
    public void getAllHistsByErdpouWhenItDoesntExist() {
        when(repository.findAllByErdpou(10010010)).thenThrow(new NoHistsException());

        assertThrows(NoHistsException.class, () -> {
            shareHistService.getAllHistsByErdpou(10010010);
        });
    }

    private ChangedShareFields prepareChangedFields() {
        List<ChangedShareField> changedShareFieldList = new ArrayList<>(Arrays.asList(
                new ChangedShareField(FieldName.NOMINAL_VALUE, 100.01, 100.00, 10010010),
                new ChangedShareField(FieldName.SHARES_NUMBER, 10, 20, 10010010),
                new ChangedShareField(FieldName.TOTAL_NOMINAL_VALUE,
                        1000.10, 2000.00, 10010010),
                new ChangedShareField(
                        FieldName.COMMENT, "Amazon", "Amazon Europe", 10010010),
                new ChangedShareField(FieldName.RELEASE_DATE,
                        new Timestamp(20001004), new Timestamp(20010203), 10010010)));
        return ChangedShareFields.builder()
                .changedShareFields(changedShareFieldList).build();
    }

    private List<ShareHist> prepareShareHists() {
        List<ChangedShareField> fieldList = prepareChangedFields().getChangedShareFields();
        List<ShareHist> histList = new ArrayList<>();
        for (ChangedShareField field : fieldList) {
            histList.add(ChangedShareFieldToShareHistConverter.convert(field));
        }
        return histList;
    }
}