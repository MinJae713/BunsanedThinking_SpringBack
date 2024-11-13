package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseVO {
    private int product_id;
    private String disease_name;
    private int disease_limit;
    private int surgeries_limit;

    public Disease getEntity(ProductVO productVO, InsuranceVO insuranceVO, List<Contract> contracts) {
        Disease disease = new Disease();
        disease.setDiseaseLimit(disease_limit);
        disease.setDiseaseName(disease_name);
        disease.setSurgeriesLimit(surgeries_limit);

        disease.setAgeRange(insuranceVO.getAge_range());
        disease.setContractPeriod(insuranceVO.getContract_period());
        disease.setCoverage(insuranceVO.getCoverage());
        disease.setInsuranceType(InsuranceType.values()[insuranceVO.getInsurance_type()]);
        disease.setMonthlyPremium(insuranceVO.getMonthly_premium());

        disease.setId(product_id);
        disease.setMaximumMoney(productVO.getMaximum_money());
        disease.setName(productVO.getName());

        disease.setContractList(contracts);
        return disease;
    }
}
