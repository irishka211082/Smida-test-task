package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.domain.*;
import com.smida.test.task.smida.exceptions.NoSharesException;
import com.smida.test.task.smida.repository.ShareRepository;
import com.smida.test.task.smida.service.ShareService;
import com.smida.test.task.smida.utils.UtilOperations;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final ShareHistServiceImpl shareHistService;

    @Autowired
    public ShareServiceImpl(ShareRepository shareRepository, ShareHistServiceImpl shareHistServiceImpl) {
        this.shareRepository = shareRepository;
        this.shareHistService = shareHistServiceImpl;
    }

    @Override
    @Transactional
    public Share create(@NonNull Share share) {
        log.info("Try to create a new share");

        share.setStatus(Status.ACTIVE);
        share.setTotalNominalValue(
                UtilOperations.calculateTotalNominalValue(
                        share.getNominalValue(),
                        share.getSharesNumber()));
        log.info("A new share was fully completed for saving to db.");

        return shareRepository.save(share);
    }

    @Override
    @Transactional
    public Share update(long id, @NonNull Share newShare) {
        log.info("Try to update te share with id {}", id);

        Share share = findById(id);
        log.debug("Find the share with id {} in the database", id);

        log.info("Try to get all changed fields");

        List<ChangedShareField> changedShareFields = getChangedFields(share, newShare);
        ChangedShareFields changedFields = new ChangedShareFields(changedShareFields);

        shareHistService.addHistory(changedFields);
        log.debug("The info about changed fields was added to history table successfully.");

        Share addedShare = shareRepository.save(share);
        log.debug("The share with id {} was added to database successfully.", addedShare.getId());
        return addedShare;
    }

    @Override
    public Share findById(long id) {
        log.info("Try to find the share by id {}", id);

        Share share = shareRepository.findById(id).orElseThrow(NoSuchElementException::new);
        log.debug("The share with id {} was found in the database.", id);

        return share;
    }

    @Override
    public @NonNull List<Share> getAllShares() {
        log.info("Try to get all shares.");

        List<Share> shares = shareRepository.findAll();
        if (Objects.nonNull(shares)) {
            log.debug("The list of shares got from the database successfully.");
        } else {
            throw new NoSharesException();
        }
        return shares;
    }

    @Override
    public List<Share> getAllShares(int erdpou) {
        log.info("Try to get all shares with the erdpou {}.", erdpou);

        List<Share> shares = shareRepository.findAllByErdpou(erdpou);
        if (Objects.nonNull(shares)) {
            log.debug("The list of shares with the erdpou {} got from database successfully.", erdpou);
        } else {
            log.debug("There are no shares with the erdpou {}!", erdpou);
            throw new NoSharesException();
        }
        return shares;
    }

    @Override
    public List<Share> getAllShares(Status status) {
        log.info("Try to get all shares with the status {}.", status);

        List<Share> shares = shareRepository.findAllByStatus(status);
        if (Objects.nonNull(shares)) {
            log.debug("The list of shares with the status {} got from database successfully.", status);
        } else {
            log.debug("There are no shares with the status {}!", status);
            throw new NoSharesException();
        }
        return shares;
    }

    @Override
    public Share delete(long id) {
        log.info("Try to delete the share by id {}", id);

        Share share = findById(id);
        share.setStatus(Status.DELETED);
        Share deletedShare = shareRepository.save(share);
        log.debug("The status of share with id {} was changed from ACTIVE to DELETED.", id);

        return deletedShare;
    }

    private void checkNominalValue(List<ChangedShareField> changedShareFields, Share share, Share newShare) {
        if (share.getNominalValue() != newShare.getNominalValue()) {
            log.info("Found that the nominal value will be changed.");
            changedShareFields.add(new ChangedShareField(
                    FieldName.NOMINAL_VALUE,
                    share.getNominalValue(),
                    newShare.getNominalValue(),
                    share.getErdpou()
            ));
            log.debug("The info about the nominal value was prepared for history-table");

            share.setNominalValue(newShare.getNominalValue());
        }
    }

    private void checkSharesNumber(List<ChangedShareField> changedShareFields, Share share, Share newShare) {
        if (share.getSharesNumber() != newShare.getSharesNumber()) {
            log.info("Found that the shares number will be changed.");
            changedShareFields.add(new ChangedShareField(
                    FieldName.SHARES_NUMBER,
                    share.getSharesNumber(),
                    newShare.getSharesNumber(),
                    share.getErdpou()
            ));
            log.debug("The info about the shares number was prepared for history-table");

            share.setSharesNumber(newShare.getSharesNumber());
        }
    }

    private void updateTotalNominalValue(List<ChangedShareField> changedShareFields, Share share) {
        double totalNominalValue = UtilOperations.calculateTotalNominalValue(
                share.getNominalValue(),
                share.getSharesNumber());
        log.debug("The total nominal value was calculated for future edited share.");

        changedShareFields.add(new ChangedShareField(
                FieldName.TOTAL_NOMINAL_VALUE,
                share.getTotalNominalValue(),
                totalNominalValue,
                share.getErdpou()

        ));
        log.debug("The info about the total nominal value was prepared for history-table");

        share.setTotalNominalValue(totalNominalValue);
    }

    private void checkComment(List<ChangedShareField> changedShareFields, Share share, Share newShare) {
        if (share.getComment().equals(newShare.getComment())) {
            log.info("Found that the comment will be changed.");
            changedShareFields.add(new ChangedShareField(
                    FieldName.COMMENT,
                    share.getComment(),
                    newShare.getComment(),
                    share.getErdpou()
            ));
            log.debug("The info about the comment was prepared for history-table");

            share.setComment(newShare.getComment());
        }
    }

    private void checkReleaseDate(List<ChangedShareField> changedShareFields, Share share, Share newShare) {
        if (share.getReleaseDate().equals(newShare.getReleaseDate())) {
            log.info("Found that the release date will be changed.");
            changedShareFields.add(new ChangedShareField(
                    FieldName.RELEASE_DATE,
                    share.getComment(),
                    newShare.getComment(),
                    share.getErdpou()
            ));
            log.debug("The info about the release date was prepared for history-table");

            share.setReleaseDate(newShare.getReleaseDate());
        }
    }

    private List<ChangedShareField> getChangedFields(Share share, Share newShare) {
        List<ChangedShareField> changedShareFields = new ArrayList<>();

        log.info("Check, which field of the share will be changed.");
        if (share.getNominalValue() != newShare.getNominalValue()
                || share.getSharesNumber() != newShare.getSharesNumber()
        ) {
            log.info("Check, if the nominal value will be changed.");
            checkNominalValue(changedShareFields, share, newShare);

            log.info("Check, if the shares number will be changed.");
            checkSharesNumber(changedShareFields, share, newShare);

            log.info("Try to update the total nominal value.");
            updateTotalNominalValue(changedShareFields, share);
        }

        log.info("Check, if the comment will be changed.");
        checkComment(changedShareFields, share, newShare);

        log.info("Check, if the release date will be changed.");
        checkReleaseDate(changedShareFields, share, newShare);

        return changedShareFields;
    }
}
