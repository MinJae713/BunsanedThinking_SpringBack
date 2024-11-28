package com.example.bunsanedthinking_springback.dto.customer.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class ComplainRequest {
    @Range(min = 0, max = 3, message = "4가지 민원 중에서 입력해주세요")
    private int complainType;
    @NotBlank(message = "민원 제목은 반드시 입력해주세요")
    private String title;
    @NotBlank(message = "민원 내용은 반드시 입력해주세요")
    private String content;
    private int customerId;
}
