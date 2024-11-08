package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVO {
    private int id;
    private String address;
    private int age;
    private String bank_account;
    private String bank_name;
    private int gender;
    private String job;
    private String name;
    private String phone_number;
    private Long property;
    private String resident_registration_number;

    public Customer getEntity(ArrayList<AccidentHistory> accidentHistoryList,
                              ArrayList<Accident> accidentList,
                              ArrayList<Complaint> complaintList,
                              ArrayList<Contract> contractList,
                              ArrayList<Counsel> counselList,
                              ArrayList<DiseaseHistory> diseaseHistoryList,
                              ArrayList<SurgeryHistory> surgeryHistoryList) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setAddress(address);
        customer.setAge(age);
        customer.setBankAccount(bank_account);
        customer.setBankName(bank_name);
        customer.setGender(Gender.values()[gender]);
        customer.setJob(job);
        customer.setName(name);
        customer.setPhoneNumber(phone_number);
        customer.setProperty(property);
        customer.setResidentRegistrationNumber(resident_registration_number);

        customer.setAccidentHistoryList(accidentHistoryList);
        customer.setAccidentList(accidentList);
        customer.setComplaintList(complaintList);
        customer.setContractList(contractList);
        customer.setCounsel(counselList);
        customer.setDiseaseHistoryList(diseaseHistoryList);
        customer.setSurgeryHistoryList(surgeryHistoryList);
        return customer;
    }
}

