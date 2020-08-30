package com.smida.test.task.smida.service.impl;

import com.smida.test.task.smida.domain.Share;
import com.smida.test.task.smida.repository.ShareRepository;
import com.smida.test.task.smida.service.ShareService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareRepository repository;

    @Override
    public Share create(@NonNull Share share) {
        share.setIsActive(1);
        share.setTotalNominalValue(calculateTotalNominalValue(share.getNominalValue(), share.getSharesNumber()));

        return repository.save(share);
    }

    @Override
    public Share update(long id, @NonNull Share newShare) {
        Share share = findById(id);

        share.setComment(newShare.getComment());
        share.setNominalValue(newShare.getNominalValue());
        share.setSharesNumber(newShare.getSharesNumber());
        share.setTotalNominalValue(newShare.getTotalNominalValue());
        share.setErdpou(newShare.getErdpou());
        share.setReleaseDate(newShare.getReleaseDate());

        return repository.save(share);
    }

    @Override
    public Share findById(long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public @NonNull List<Share> getAllShares() {
        return repository.findAll();
    }

    @Override
    public Share delete(long id) {
        Share share = findById(id);
        share.setIsActive(0);

        return repository.save(share);
    }

    private double calculateTotalNominalValue(double nominalValue, int sharesNumber) {
        return nominalValue * sharesNumber;
    }
}
