package com.example.bunsanedthinking_springback.model.domain.disease;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseDModel {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InsuranceMapper insuranceMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;
    public Disease getById(int id) {
        ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
        InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(id);
        DiseaseVO diseaseVO = diseaseMapper.getById_Customer(id).orElse(null);
        String diseaseName = diseaseVO.getDisease_name();
        int diseaseLimit = diseaseVO.getDisease_limit();
        int surgeriesLimit = diseaseVO.getSurgeries_limit();
        return new Disease(productVO, insuranceVO, diseaseName, diseaseLimit, surgeriesLimit);
    }
    public List<Disease> getAll() {
        List<Disease> diseases = new ArrayList<Disease>();
        diseaseMapper.getAll_Customer().stream().forEach(e -> diseases.add(getById(e.getProduct_id())));
        return diseases;
    }
    public int getMaxId() {
        return diseaseMapper.getMaxId();
    }
    public void add(DiseaseVO diseaseVO) {
        diseaseMapper.insert_ProductManagement(diseaseVO);
    }
    public void update(DiseaseVO diseaseVO) {
        diseaseMapper.update_ProductManagementModel(diseaseVO);
    }
    public void delete(int id) {
        diseaseMapper.deleteById(id);
    }
}
