package com.smida.test.task.smida.repository;

import com.smida.test.task.smida.domain.ShareHist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistRepository extends JpaRepository<ShareHist, Long> {

    List<ShareHist> findAllByErdpou(int erdpou);
}
