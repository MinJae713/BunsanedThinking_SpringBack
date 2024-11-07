package com.example.bunsanedthinking_springback.model.domain.injury;

import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.repository.InjuryMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InjuryDModel {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InsuranceMapper insuranceMapper;
    @Autowired
    private InjuryMapper injuryMapper;
    public Injury getById(int id) {
        ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
        InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(id);
        InjuryVO injuryVO = injuryMapper.getById_Customer(id).orElse(null);
        return new Injury(productVO, insuranceVO,
                InjuryType.values()[injuryVO.getInjury_type()],
                injuryVO.getSurgeries_limit()
        );
    }
    public List<Injury> getAll() {
        List<Injury> injuries = new ArrayList<Injury>();
        injuryMapper.getAll_Customer().stream().forEach(e -> injuries.add(getById(e.getProduct_id())));
        return injuries;
    }
    public int getMaxId() {
        return injuryMapper.getMaxId();
    }
    public void add(InjuryVO injuryVO) {
        injuryMapper.insert_ProductManagement(injuryVO);
    }
    public void update(InjuryVO injuryVO) {
        injuryMapper.update_ProductManagementModel(injuryVO);
    }
    public void delete(int id) {
        injuryMapper.deleteById(id);
    }
}
