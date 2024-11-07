package com.example.bunsanedthinking_springback.model.domain.automobile;

import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.repository.AutomobileMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.repository.ServiceMapper;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.ServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutomobileDModel {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InsuranceMapper insuranceMapper;
    @Autowired
    private AutomobileMapper automobileMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    public Automobile getById(int id) {
        ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
        InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(id);
        AutoMobileVO autoMobileVO = automobileMapper.getById_Customer(id).orElse(null);
        List<ServiceVO> serviceVOS = serviceMapper.getAllByProductId_Customer(id);
        ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
        serviceVOS.stream().map(s -> s.getService()).forEach(s -> serviceTypeList.add(ServiceType.values()[s]));
        VehicleType vehicleType = VehicleType.values()[autoMobileVO.getVehicle_type()];
        int accidentLimit = autoMobileVO.getAccident_limit();
        return new Automobile(productVO, insuranceVO, accidentLimit, vehicleType, serviceTypeList);
    }
    public List<Automobile> getAll() {
        List<Automobile> automobiles = new ArrayList<>();
        automobileMapper.getAll_Customer().stream().forEach(e -> automobiles.add(getById(e.getProduct_id())));
        return automobiles;
    }
    public int getMaxId() {
        return automobileMapper.getMaxId();
    }
    public void add(AutoMobileVO autoMobileVO) {
        automobileMapper.insert_ProductManagement(autoMobileVO);
    }
    public void update(AutoMobileVO autoMobileVO) {
        automobileMapper.update_ProductManagementModel(autoMobileVO);
    }
    public void delete(int id) {
        automobileMapper.deleteById(id);
    }
}
