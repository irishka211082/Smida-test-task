package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.utils.UtilOperations;
import com.smida.test.task.smida.domain.*;
import com.smida.test.task.smida.repository.ShareRepository;
import com.smida.test.task.smida.service.ShareHistService;
import com.smida.test.task.smida.service.ShareService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final ShareHistService shareHistService;

    @Autowired
    public ShareServiceImpl(ShareRepository shareRepository, ShareHistServiceImpl shareHistServiceImpl) {
        this.shareRepository = shareRepository;
        this.shareHistService = shareHistServiceImpl;
    }

    @Override
    @Transactional
    public Share create(@NonNull Share share) {
        share.setStatus(Status.ACTIVE);
        share.setTotalNominalValue(
                UtilOperations.calculateTotalNominalValue(
                        share.getNominalValue(),
                        share.getSharesNumber()));

        return shareRepository.save(share);
    }

    @Override
    @Transactional
    public Share update(long id, @NonNull Share newShare) {
        Share share = findById(id);

        List<ChangedShareField> changedShareFields = new ArrayList<>();

        if (share.getNominalValue() != newShare.getNominalValue()
                || share.getSharesNumber() != newShare.getSharesNumber()
        ) {
            if (share.getNominalValue() != newShare.getNominalValue()) {
                changedShareFields.add(new ChangedShareField(
                        ShareChangedFieldName.NOMINAL_VALUE,
                        share.getNominalValue(),
                        newShare.getNominalValue(),
                        share.getErdpou()
                ));

                share.setNominalValue(newShare.getNominalValue());
            }

            if (share.getSharesNumber() != newShare.getSharesNumber()) {
                changedShareFields.add(new ChangedShareField(
                        ShareChangedFieldName.SHARES_NUMBER,
                        share.getSharesNumber(),
                        newShare.getSharesNumber(),
                        share.getErdpou()
                        ));

                share.setSharesNumber(newShare.getSharesNumber());
            }

            double totalNominalValue = UtilOperations.calculateTotalNominalValue(
                    share.getNominalValue(),
                    share.getSharesNumber());

            changedShareFields.add(new ChangedShareField(
                    ShareChangedFieldName.TOTAL_NOMINAL_VALUE,
                    share.getTotalNominalValue(),
                    totalNominalValue,
                    share.getErdpou()
            ));

            share.setTotalNominalValue(totalNominalValue);
        }

        if (share.getComment().equals(newShare.getComment())) {
            changedShareFields.add(new ChangedShareField(
                    ShareChangedFieldName.COMMENT,
                    share.getComment(),
                    newShare.getComment(),
                    share.getErdpou()
            ));

            share.setComment(newShare.getComment());
        }

        if (share.getReleaseDate().equals(newShare.getReleaseDate())) {
            changedShareFields.add(new ChangedShareField(
                    ShareChangedFieldName.RELEASE_DATE,
                    share.getComment(),
                    newShare.getComment(),
                    share.getErdpou()
            ));

            share.setReleaseDate(newShare.getReleaseDate());
        }

        ChangedShareFields changedFields = new ChangedShareFields(changedShareFields);
        shareHistService.create(changedFields);

        return shareRepository.save(share);
    }

    @Override
    public Share findById(long id) {
        return shareRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public @NonNull List<Share> getAllShares() {
        return shareRepository.findAll();
    }

    @Override
    public List<Share> getAllShares(int erdpou) {
        return shareRepository.findAllByErdpou(erdpou);
    }

    @Override
    public List<Share> getAllShares(Status status) {
        return shareRepository.findAllByStatus(status);
    }

    @Override
    public Share delete(long id) {
        Share share = findById(id);
        share.setStatus(Status.DELETED);

        return shareRepository.save(share);
    }
}
