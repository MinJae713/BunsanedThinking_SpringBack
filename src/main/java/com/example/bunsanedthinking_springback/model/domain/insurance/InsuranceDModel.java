package com.example.bunsanedthinking_springback.model.domain.insurance;

import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceDModel {
    @Autowired
    private InsuranceMapper insuranceMapper;
    public Insurance getById(int id) {
        // 추상타입이라 반환 불가
        return null;
    }
    public List<Insurance> getAll() {
        // 추상타입이라 반환 불가
        return null;
    }
    public int getMaxId() {
        return insuranceMapper.getMaxId_ProductManagementModel();
    }
    public void add(InsuranceVO insuranceVO) {
        insuranceMapper.insert_ProductManagement(insuranceVO);
    }
    public void update(InsuranceVO insuranceVO) {
        insuranceMapper.update_ProductManagementModel(insuranceVO);
    }
    public void delete(int id) {
        insuranceMapper.delete(id);
    }
}
