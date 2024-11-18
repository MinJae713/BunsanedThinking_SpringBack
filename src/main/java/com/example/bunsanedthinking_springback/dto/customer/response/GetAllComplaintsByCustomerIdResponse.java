package com.example.bunsanedthinking_springback.dto.customer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllComplaintsByCustomerIdResponse {
    private String type;
    private int id;
    private String title;
    private String postDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date processingDate;
    private String status;
}
