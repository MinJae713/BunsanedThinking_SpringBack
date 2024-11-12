package com.example.bunsanedthinking_springback.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplainDTO {
    private int complainType;
    private String title;
    private String content;
    private int customerId;
}
