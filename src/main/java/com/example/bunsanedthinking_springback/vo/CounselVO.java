package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounselVO {
    private int id;
    private LocalDate counsel_date;
    private int process_status;
    private int customer_id;
    private int product_id;

    public Counsel getEntity(String name,
                             String phoneNumber,
                             String job, int age,
                             Gender gender) {
        Counsel counsel = new Counsel();
        counsel.setId(id);
        counsel.setCounselDate(Date.valueOf(counsel_date));
        counsel.setCustomerID(customer_id);
        counsel.setProcessStatus(CounselProcessStatus.values()[process_status]);
        counsel.setProductID(product_id);

        counsel.setName(name);
        counsel.setPhoneNumber(phoneNumber);
        counsel.setJob(job);
        counsel.setAge(age);
        counsel.setGender(gender);
        return counsel;
    }
}
