package com.youbout.batchperformance.parallelsteps.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class DestinationAccountBalanceVO {
    private long accountId;
    private double balance;
}
