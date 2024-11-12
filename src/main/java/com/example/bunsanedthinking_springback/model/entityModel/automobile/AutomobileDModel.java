package com.example.bunsanedthinking_springback.model.entityModel.automobile;

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
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById(id).orElse(null);
		if (insuranceVO == null)
			return null;
		AutoMobileVO autoMobileVO = automobileMapper.getById_Customer(id).orElse(null);
		if (autoMobileVO == null)
			return null;
		List<ServiceVO> serviceVOS = serviceMapper.getAllByProductId_Customer(id);
		ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
		serviceVOS.stream()
			.map(ServiceVO::getService)
			.map(ServiceType::indexOf)
			.forEach(serviceTypeList::add);
		VehicleType vehicleType = VehicleType.indexOf(autoMobileVO.getVehicle_type());
		int accidentLimit = autoMobileVO.getAccident_limit();
		return new Automobile(productVO, insuranceVO, accidentLimit, vehicleType, serviceTypeList);
	}

	public List<Automobile> getAll() {
		List<Automobile> automobiles = new ArrayList<>();
		automobileMapper.getAll_Customer()
			.forEach(e -> automobiles.add(getById(e.getProduct_id())));
		return automobiles;
	}

	public Integer getMaxId() {
		return automobileMapper.getMaxId();
	}

	public void add(Automobile autoMobile) {
		if (autoMobile == null) return;
		if (automobileMapper.getById_Customer(autoMobile.getId()).isPresent()) return;
		productMapper.insert_LoanManagement(autoMobile.findProductVO());
		insuranceMapper.insert(autoMobile.findInsuranceVO());
		automobileMapper.insert_ProductManagement(autoMobile.findVO());
		List<ServiceType> serviceTypes = autoMobile.getServiceList();
		if (serviceTypes == null) return;
		serviceTypes.forEach(e -> serviceMapper.insert_ProductManagement(
				new ServiceVO(autoMobile.getId(), e.ordinal())
		));
	}

	public void update(Automobile autoMobile) {
		if (autoMobile == null) return;
		if (automobileMapper.getById_Customer(autoMobile.getId()).isEmpty()) return;
		// service는 수정이 아니라 삭제 후 다시 추가하는 방법임
		// productId 맞는 service들 삭제 후 파라미터에 있는 서비스들 다시 추가
		List<ServiceType> serviceTypes = autoMobile.getServiceList();
		if (serviceTypes != null) {
			serviceMapper.delete_ProductManagementModel(autoMobile.getId());
			serviceTypes.forEach(e -> serviceMapper.insert_ProductManagement(
					new ServiceVO(autoMobile.getId(), e.ordinal())
			));
		}
		automobileMapper.update_ProductManagementModel(autoMobile.findVO());
		insuranceMapper.update(autoMobile.findInsuranceVO());
		productMapper.update_LoanManagement(autoMobile.findProductVO());
	}

	public void delete(int id) {
		if (automobileMapper.getById_Customer(id).isEmpty()) return;
		serviceMapper.delete_ProductManagementModel(id);
		automobileMapper.deleteById(id);
		insuranceMapper.deleteById(id);
		productMapper.delete_ProductManagementModel(id);
	}
}
