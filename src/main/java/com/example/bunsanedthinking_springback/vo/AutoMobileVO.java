package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoMobileVO {
    private int product_id;
    private int vehicle_type;
    private int accident_limit;

    public Automobile getEntity(ProductVO productVO, InsuranceVO insuranceVO, List<Contract> contractList) {
        Automobile automobile = new Automobile();
        automobile.setVehicleType(VehicleType.values()[vehicle_type]);
        automobile.setAccidentLimit(accident_limit);

        automobile.setAgeRange(insuranceVO.getAge_range());
        automobile.setContractPeriod(insuranceVO.getContract_period());
        automobile.setCoverage(insuranceVO.getCoverage());
        automobile.setInsuranceType(InsuranceType.values()[insuranceVO.getInsurance_type()]);
        automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());

        automobile.setId(product_id);
        automobile.setMaximumMoney(productVO.getMaximum_money());
        automobile.setName(productVO.getName());

        automobile.setContractList(contractList);

        return automobile;
    }
}
