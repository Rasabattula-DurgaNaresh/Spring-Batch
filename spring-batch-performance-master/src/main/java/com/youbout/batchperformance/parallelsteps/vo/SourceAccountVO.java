package com.youbout.batchperformance.parallelsteps.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class SourceAccountVO {
    private long id;
    private String accountNumber;
    private LocalDateTime createdAt;
}
