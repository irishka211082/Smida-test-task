package com.smida.test.task.smida.service;

import com.smida.test.task.smida.domain.ChangedShareFields;
import com.smida.test.task.smida.domain.ShareHist;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface ShareHistService {

    List<ShareHist> addHistory(@NonNull ChangedShareFields changedFields);

    List<ShareHist> getAllHists();

    List<ShareHist> getAllHists(@PageableDefault PageRequest pageRequest);

    List<ShareHist> getAllHistsByErdpou(int erdpou);
}
