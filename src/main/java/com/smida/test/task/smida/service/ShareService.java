package com.smida.test.task.smida.service;

import com.smida.test.task.smida.domain.Share;
import com.smida.test.task.smida.domain.Status;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface ShareService {

    Share create(@NonNull Share share);

    Share update(long id, @NonNull Share share);

    Share findById(long id);

    @NonNull
    List<Share> getAllShares();

    List<Share> getAllShares(@PageableDefault PageRequest pageRequest);

    List<Share> getAllShares(int erdpou);

    List<Share> getAllShares(Status status);

    Share delete(long id);
}
