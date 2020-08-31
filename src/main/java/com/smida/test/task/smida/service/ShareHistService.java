package com.smida.test.task.smida.service;

import com.smida.test.task.smida.domain.ChangedShareFields;
import lombok.NonNull;

public interface ShareHistService {

    void create(@NonNull ChangedShareFields changedFields);
}
