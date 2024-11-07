package com.example.bunsanedthinking_springback.model.administrative;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.global.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.OfficeSupplyMapper;
import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrativeModel {

	@Autowired
	private OfficeSupplyMapper officeSupplyMapper;

	public void addOfficeSupply(String name, String description, int inventory, int total_inventory,
		int department_id) throws DuplicateOfficeSupplyException {
		OfficeSupplyVO existingSupplyVO = officeSupplyMapper.findByName_OfficeSupply(name);
		if (existingSupplyVO != null) {
			throw new DuplicateOfficeSupplyException();
		}
		//현재 최대 ID를 가져온다
		Integer maxId = officeSupplyMapper.getMaxId_Administrative();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "1");
		} else {
			String index = (maxId + "").substring((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "").length());
			id = Integer.parseInt((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}
		OfficeSupplyVO officeSupplyVO = new OfficeSupplyVO();
		officeSupplyVO.setId(id); // 새로운 ID 설정
		officeSupplyVO.setName(name);
		officeSupplyVO.setDescription(description);
		officeSupplyVO.setInventory(inventory);
		officeSupplyVO.setTotal_inventory(total_inventory);
		officeSupplyVO.setDepartment_id(department_id);
		officeSupplyMapper.insert_OfficeSupply(officeSupplyVO);
	}

	public void deleteOfficeSupply(int id) throws NotExistException {
		OfficeSupplyVO officeSupplyVO = officeSupplyMapper.findById_OfficeSupply(id);
		if (officeSupplyVO == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		officeSupplyMapper.delete_OfficeSupply(id);
	}

	public OfficeSupplyVO getOfficeSupply(int id) throws NotExistException {
		OfficeSupplyVO officeSupplyVO = officeSupplyMapper.findById_OfficeSupply(id);
		if (officeSupplyVO == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		return officeSupplyVO;
	}

	public void updateOfficeSupply(int index, String input, int id) throws NotExistException {
		OfficeSupplyVO officeSupplyVO = officeSupplyMapper.findById_OfficeSupply(id);
		if (officeSupplyVO == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1 -> officeSupplyVO.setName(input);
			case 2 -> officeSupplyVO.setDescription(input);
			case 3 -> officeSupplyVO.setInventory(Integer.parseInt(input));
			default -> throw new IllegalArgumentException("잘못된 선택입니다. 다시 선택해주세요.");
		}
		officeSupplyMapper.update_OfficeSupply(officeSupplyVO);
	}

	public List<OfficeSupplyVO> getAllOfficeSupplies() {
		return officeSupplyMapper.getAll_OfficeSupply();
	}

	public int getTotalInventory() {
		return officeSupplyMapper.getTotalInventory_OfficeSupply();
	}
}
