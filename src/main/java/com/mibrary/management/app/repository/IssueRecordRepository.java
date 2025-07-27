package com.mibrary.management.app.repository;

import com.mibrary.management.app.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {

}
