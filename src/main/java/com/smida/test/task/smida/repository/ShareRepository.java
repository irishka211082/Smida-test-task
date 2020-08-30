package com.smida.test.task.smida.repository;

import com.smida.test.task.smida.domain.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
}
