package com.youbout.batchperformance.parallelsteps.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class SourceTransactionVO {
    private long id;
    private LocalDate transactionDate;
    private long accountId;
    private double amount;
    private LocalDateTime createdAt;
}
