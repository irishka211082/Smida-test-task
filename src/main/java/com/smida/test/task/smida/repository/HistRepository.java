package com.smida.test.task.smida.repository;

import com.smida.test.task.smida.domain.ShareHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistRepository extends JpaRepository<ShareHist, Long> {
}
