package com.mibrary.management.app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRecordDTO {

    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Boolean isReturned;
}
