package com.smida.test.task.smida.repository;

import com.smida.test.task.smida.domain.Share;
import com.smida.test.task.smida.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share, Long> {

    List<Share> findAll();

    List<Share> findAllByErdpou(int erdpou);

    List<Share> findAllByStatus(Status status);
}
