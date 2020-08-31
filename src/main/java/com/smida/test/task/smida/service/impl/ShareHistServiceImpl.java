package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.controller.converter.ChangedShareFieldToShareHistConverter;
import com.smida.test.task.smida.domain.ChangedShareField;
import com.smida.test.task.smida.domain.ChangedShareFields;
import com.smida.test.task.smida.domain.ShareHist;
import com.smida.test.task.smida.repository.HistRepository;
import com.smida.test.task.smida.service.ShareHistService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShareHistServiceImpl implements ShareHistService {

    private final HistRepository histRepository;

    @Autowired
    public ShareHistServiceImpl(HistRepository histRepository) {
        this.histRepository = histRepository;
    }

    @Override
    public void create(@NonNull ChangedShareFields changedFields) {

        List<ShareHist> shareHists = new ArrayList<>();

        List<ChangedShareField> shareFields = changedFields.getShares();

        for (ChangedShareField shareField : shareFields) {
            ShareHist hist = ChangedShareFieldToShareHistConverter.convert(shareField);
            shareHists.add(hist);
        }
        histRepository.saveAll(shareHists);
    }
}
