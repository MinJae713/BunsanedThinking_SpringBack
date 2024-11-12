package com.example.bunsanedthinking_springback.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@NoArgsConstructor
public class ReceiveInsuranceDTO {
    private int contractId;
    private int customerId;
    private BufferedImage medicalCertificateImage;
    private BufferedImage receiptImage;
    private BufferedImage residentRegistrationCardImage;
}
