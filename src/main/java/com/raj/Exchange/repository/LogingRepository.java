package com.raj.Exchange.repository;

import com.raj.Exchange.model.LogTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogingRepository extends JpaRepository<LogTable, Long> {
}
