package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewComplaintResponse {
    private String type;
    private int id;
    private String title;
    private String postDate;
    private String processingDate; // Date -> String
    private String status;

    public static ViewComplaintResponse of(Complaint complaint) {
        String type = complaint.getComplaintType().getName();
        int complaintId = complaint.getId();
        String title = complaint.getTitle();
        String postDate = complaint.getPostDate();
        java.util.Date processingDate = complaint.getProcessingDate();
        String status = complaint.getProcessStatus().getText();
        return new ViewComplaintResponse(type, complaintId,
                title, postDate, DateUtils.toString(processingDate), status);
    }
}
