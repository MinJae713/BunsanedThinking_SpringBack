package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
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

    public static GetAllComplaintsByCustomerIdResponse of(Complaint complaint) {
        String type = complaint.getComplaintType().getName();
        int complaintId = complaint.getId();
        String title = complaint.getTitle();
        String postDate = complaint.getPostDate();
        java.util.Date processingDate = complaint.getProcessingDate();
        String status = complaint.getProcessStatus().getText();
        return new GetAllComplaintsByCustomerIdResponse(type, complaintId,
                title, postDate, processingDate, status);
    }
}
