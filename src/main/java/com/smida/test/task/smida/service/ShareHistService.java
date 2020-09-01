package com.smida.test.task.smida.service;

import com.smida.test.task.smida.domain.ChangedShareFields;
import com.smida.test.task.smida.domain.ShareHist;
import lombok.NonNull;

import java.util.List;

public interface ShareHistService {

    void create(@NonNull ChangedShareFields changedFields);

    List<ShareHist> getAllHists();

    List<ShareHist> getAllHistsByErdpou(int erdpou);
}
