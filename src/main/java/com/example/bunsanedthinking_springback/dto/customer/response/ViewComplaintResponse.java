package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String status = complaint.getProcessStatus().getText();
        return new ViewComplaintResponse(type, complaintId,
                title, postDate, formatter.format(processingDate), status);
    }
}
