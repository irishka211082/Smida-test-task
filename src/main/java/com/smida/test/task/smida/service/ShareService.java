package com.smida.test.task.smida.service;

import com.smida.test.task.smida.domain.Share;
import com.smida.test.task.smida.domain.Status;
import lombok.NonNull;

import java.util.List;

public interface ShareService {

    Share create(@NonNull Share share);

    Share update(long id, @NonNull Share share);

    Share findById(long id);

    @NonNull
    List<Share> getAllShares();

    List<Share> getAllShares(int erdpou);

    List<Share> getAllShares(Status status);

    Share delete(long id);
}
