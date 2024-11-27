package com.example.bunsanedthinking_springback.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class ReceiveInsuranceRequest {
    private int contractId;
    private MultipartFile medicalCertificateImage;
    private MultipartFile receiptImage;
    private MultipartFile residentRegistrationCardImage;
}
