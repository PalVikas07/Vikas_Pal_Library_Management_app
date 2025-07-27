package com.mibrary.management.app.controller;

import com.mibrary.management.app.entity.IssueRecord;
import com.mibrary.management.app.service.IssueRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issuerecords")
public class IssueRecordController {

    private IssueRecordService issueRecordService;

    @PostMapping("/issuethebook/{bookid}")
    public ResponseEntity <IssueRecord> issueTheBook(@PathVariable Long bookId){
        return ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
    }

    @PostMapping("/returnthebook/{issuerecordid}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId){
        return ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
    }

}
