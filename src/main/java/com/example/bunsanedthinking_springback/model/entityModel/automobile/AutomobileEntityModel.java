package com.example.bunsanedthinking_springback.model.entityModel.automobile;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.ServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutomobileEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private AutomobileMapper automobileMapper;
	@Autowired
	private CounselEntityModel counselEntityModel;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private EndorsementMapper endorsementMapper;
	@Autowired
	private RevivalMapper revivalMapper;
	@Autowired
	private TerminationMapper terminationMapper;
	@Autowired
	private RecontractMapper recontractMapper;

	@ReadOnly
	public Automobile getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById(id).orElse(null);
		if (insuranceVO == null)
			return null;
		AutoMobileVO autoMobileVO = automobileMapper.getById(id).orElse(null);
		if (autoMobileVO == null)
			return null;
		List<ServiceVO> serviceVOS = serviceMapper.getById(id);
		ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
		serviceVOS.stream()
			.map(ServiceVO::getService)
			.map(ServiceType::indexOf)
			.forEach(serviceTypeList::add);
		VehicleType vehicleType = VehicleType.indexOf(autoMobileVO.getVehicle_type());
		int accidentLimit = autoMobileVO.getAccident_limit();
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getProductId() == id).toList();
		List<Counsel> counsels = counselEntityModel.getAll().
				stream().filter(e -> e.getProductID() == id).toList();
		return new Automobile(productVO, insuranceVO, accidentLimit, vehicleType, serviceTypeList, contracts, counsels);
	}
	@ReadOnly
	public List<Automobile> getAll() {
		List<Automobile> automobiles = new ArrayList<>();
		automobileMapper.getAll()
			.forEach(e -> automobiles.add(getById(e.getProduct_id())));
		return automobiles;
	}
	@ReadOnly
	public Integer getMaxId() {
		return automobileMapper.getMaxId();
	}

	public void add(Automobile autoMobile) {
		if (autoMobile == null) return;
		if (automobileMapper.getById(autoMobile.getId()).isPresent()) return;
		productMapper.insert(autoMobile.findProductVO());
		insuranceMapper.insert(autoMobile.findInsuranceVO());
		automobileMapper.insert(autoMobile.findVO());
		List<ServiceType> serviceTypes = autoMobile.getServiceList();
		if (serviceTypes == null) return;
		serviceTypes.forEach(e -> serviceMapper.insert(
				new ServiceVO(autoMobile.getId(), e.ordinal())
		));
		if (autoMobile.getCounselList() != null)
			autoMobile.getCounselList().forEach(e -> counselEntityModel.add(e));
		if (autoMobile.getContractList() != null)
			autoMobile.getContractList().forEach(e -> contractEntityModel.add(e));
	}

	public void update(Automobile autoMobile) {
		if (autoMobile == null) return;
		if (automobileMapper.getById(autoMobile.getId()).isEmpty()) return;
		// service는 수정이 아니라 삭제 후 다시 추가하는 방법임
		// productId 맞는 service들 삭제 후 파라미터에 있는 서비스들 다시 추가
		List<Counsel> counsels = autoMobile.getCounselList();
		if (counsels != null) counsels.forEach(e -> counselEntityModel.update(e));
		List<Contract> contracts = autoMobile.getContractList();
		if (contracts != null) contracts.forEach(e -> contractEntityModel.update(e));
		List<ServiceType> serviceTypes = autoMobile.getServiceList();
		if (serviceTypes != null) {
			serviceMapper.deleteById(autoMobile.getId());
			serviceTypes.forEach(e -> serviceMapper.insert(
					new ServiceVO(autoMobile.getId(), e.ordinal())
			));
		}
		automobileMapper.update(autoMobile.findVO());
		insuranceMapper.update(autoMobile.findInsuranceVO());
		productMapper.update(autoMobile.findProductVO());
	}

	public void delete(int id) {
		if (automobileMapper.getById(id).isEmpty()) return;
		contractEntityModel.getAll().stream().
				filter(e -> e.getProductId() == id).
				forEach(e -> deleteContract(e));
		counselEntityModel.getAll().stream().
				filter(e -> e.getProductID() == id).
				forEach(e -> counselEntityModel.delete(e.getId()));
		serviceMapper.deleteById(id);
		automobileMapper.deleteById(id);
		insuranceMapper.deleteById(id);
		productMapper.deleteById(id);
	}

	private void deleteContract(Contract contract) {
		endorsementMapper.deleteById(contract.getId());
		revivalMapper.deleteById(contract.getId());
		terminationMapper.deleteById(contract.getId());
		recontractMapper.deleteById(contract.getId());
		contractEntityModel.delete(contract.getId());
	}
}
