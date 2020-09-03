package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.converter.ChangedShareFieldToShareHistConverter;
import com.smida.test.task.smida.domain.ChangedShareField;
import com.smida.test.task.smida.domain.ChangedShareFields;
import com.smida.test.task.smida.domain.ShareHist;
import com.smida.test.task.smida.exceptions.NoHistsException;
import com.smida.test.task.smida.repository.HistRepository;
import com.smida.test.task.smida.service.ShareHistService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ShareHistServiceImpl implements ShareHistService {

    private final HistRepository histRepository;

    @Autowired
    public ShareHistServiceImpl(HistRepository histRepository) {
        this.histRepository = histRepository;
    }

    @Override
    public List<ShareHist> addHistory(@NonNull ChangedShareFields changedFields) {
        log.info("Try to add new history to database.");

        List<ShareHist> shareHists = new ArrayList<>();

        List<ChangedShareField> shareFields = changedFields.getChangedShareFields();
        log.debug("List of share fields got successfully.");

        for (ChangedShareField shareField : shareFields) {
            ShareHist hist = ChangedShareFieldToShareHistConverter.convert(shareField);
            shareHists.add(hist);
            log.debug("Changed share field was prepared for adding to db.");
        }
        List<ShareHist> shareHistsAdded = histRepository.saveAll(shareHists);
        log.debug("All changed fields was added successfully to hist-table.");
        return shareHistsAdded;
    }

    @Override
    public List<ShareHist> getAllHists() {
        log.info("Try to get all records from history-table.");

        List<ShareHist> hists = histRepository.findAll();
        if (Objects.nonNull(hists)) {
            log.debug("The list of hists got from the database successfully.");
        } else {
            throw new NoHistsException();
        }
        return hists;
    }

    @Override
    public List<ShareHist> getAllHistsByErdpou(int erdpou) {
        log.info("Try to get all records from history-table by erdpou {}.", erdpou);

        List<ShareHist> allByErdpou = histRepository.findAllByErdpou(erdpou);
        if (Objects.nonNull(allByErdpou)) {
            log.debug("The list of hists with the given erdpou got from the database successfully.");
        } else {
            log.debug("There are no hists with the erdpou {}!", erdpou);
            throw new NoHistsException();
        }
        return allByErdpou;
    }
}
