package com.example.bunsanedthinking_springback.model.domain.collateral;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollateralDModel {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private CollateralMapper collateralMapper;
    public Collateral getById(int id) {
        ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
        LoanVO loanVO = loanMapper.findById_LoanManagement(id).orElse(null);
        CollateralVO collateralVO = collateralMapper.getById_Customer(id).orElse(null);
        CollateralType collateralType = CollateralType.values()[collateralVO.getCollateral_type()];
        int minimumValue = collateralVO.getMinimum_value();
        return new Collateral(productVO, loanVO, collateralType, minimumValue);
    }
    public List<Collateral> getAll() {
        List<Collateral> collaterals = new ArrayList<Collateral>();
        collateralMapper.getAll_Customer().stream().forEach(e -> collaterals.add(getById(e.getProduct_id())));
        return collaterals;
    }
    public int getMaxId() {
        return collateralMapper.getMaxId();
    }
    public void add(CollateralVO collateralVO) {
        collateralMapper.insert_LoanManagement(collateralVO);
    }
    public void update(CollateralVO collateralVO) {
        collateralMapper.update_LoanManagement(collateralVO);
    }
    public void delete(int id) {
        collateralMapper.delete_LoanManagement(id);
    }
}
