package com.smida.test.task.smida.service;

import com.smida.test.task.smida.domain.Share;
import lombok.NonNull;

import javax.transaction.Transactional;
import java.util.List;

public interface ShareService {

    Share create(@NonNull Share share);

    @Transactional
    Share update(long id, @NonNull Share share);

    Share findById(long id);

    @NonNull
    List<Share> getAllShares();

    Share delete(long id);
}
