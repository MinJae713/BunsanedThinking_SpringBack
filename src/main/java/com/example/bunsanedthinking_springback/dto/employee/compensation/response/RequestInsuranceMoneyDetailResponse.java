package com.example.bunsanedthinking_springback.dto.employee.compensation.response;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInsuranceMoneyDetailResponse {
    private int id;
    private String productType;
    private String applyDate;
    private String customerName;
    private String processStatus;
    private String medicalCertificate;
    private String receipt;
    private String residentRegistrationCard;

    public static RequestInsuranceMoneyDetailResponse of
            (InsuranceMoney insuranceMoney, Insurance insurance, Customer customer) throws IOException {
        int id = insuranceMoney.getId();
        String productType = insurance.getInsuranceType().getName();
        Date applyDate = insuranceMoney.getApplyDate();
        String customerName = customer.getName();
        String processStatus = insuranceMoney.getProcessStatus().getName();
//        byte[] medicalCertificate = imageToByteArray(insuranceMoney.getMedicalCertificate());
//        byte[] receipt = imageToByteArray(insuranceMoney.getReceipt());
//        byte[] residentRegistrationCard = imageToByteArray(insuranceMoney.getResidentRegistrationCard());
        String medicalCertificate = Base64.getEncoder().encodeToString(getImage("medical.jpeg"));
        String receipt = Base64.getEncoder().encodeToString(getImage("recipt.jpeg"));
        String residentRegistrationCard = Base64.getEncoder().encodeToString(getImage("register.jpeg"));
        return new RequestInsuranceMoneyDetailResponse(id, productType, DateUtils.toString(applyDate),
                customerName, processStatus, medicalCertificate, receipt, residentRegistrationCard);
    }

    private static byte[] getImage(String filename) throws IOException {
        Path imagePath = Paths.get("src/main/resources/static/images/" + filename);
        return Files.readAllBytes(imagePath.toAbsolutePath());
    }

    private static byte[] imageToByteArray(Image image) throws IOException {
        // 1. Image를 BufferedImage로 변환
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);

        // 2. BufferedImage를 ByteArrayOutputStream으로 쓰기
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpeg", baos);
        baos.flush();

        // 3. ByteArrayOutputStream에서 byte[] 추출
        byte[] imageBytes = baos.toByteArray();
        baos.close();

        return imageBytes;
    }
}
