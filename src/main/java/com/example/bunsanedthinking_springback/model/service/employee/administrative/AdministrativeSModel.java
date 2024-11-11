package com.example.bunsanedthinking_springback.model.service.employee.administrative;

import com.example.bunsanedthinking_springback.dto.administrative.AddOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.dto.mo.UpdateOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.global.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.domain.officeSupply.OfficeSupplyDModel;
import com.example.bunsanedthinking_springback.repository.OfficeSupplyMapper;
import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrativeSModel {

	@Autowired
	private OfficeSupplyDModel officeSupplyDModel;
	@Autowired
	private OfficeSupplyMapper officeSupplyMapper;

//	public void addOfficeSupply(String name, String description, int inventory, int total_inventory,
//		int department_id) throws DuplicateOfficeSupplyException {
//		OfficeSupplyVO existingSupplyVO = officeSupplyMapper.findByName_OfficeSupply(name);
//		if (existingSupplyVO != null) {
//			throw new DuplicateOfficeSupplyException();
//		}
//		//현재 최대 ID를 가져온다
//		Integer maxId = officeSupplyMapper.getMaxId_Administrative();
//		int id;
//		if (maxId == null) {
//			id = Integer.parseInt(OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "1");
//		} else {
//			String index = (maxId + "").substring((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "").length());
//			id = Integer.parseInt((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
//		}
//		OfficeSupplyVO officeSupplyVO = new OfficeSupplyVO();
//		officeSupplyVO.setId(id); // 새로운 ID 설정
//		officeSupplyVO.setName(name);
//		officeSupplyVO.setDescription(description);
//		officeSupplyVO.setInventory(inventory);
//		officeSupplyVO.setTotal_inventory(total_inventory);
//		officeSupplyVO.setDepartment_id(department_id);
//		officeSupplyMapper.insert_OfficeSupply(officeSupplyVO);
//	}
	public void addOfficeSupply(AddOfficeSupplyDTO addOfficeSupplyDTO) throws DuplicateOfficeSupplyException {
		boolean isExistOfficeSupplyName = officeSupplyDModel.getAll().stream()
			.anyMatch(officeSupply ->
					officeSupply.getName().equals(addOfficeSupplyDTO.getName()));
		if(isExistOfficeSupplyName)
			throw new DuplicateOfficeSupplyException();
		//현재 최대 ID를 가져온다
		Integer maxId = officeSupplyDModel.getMaxId();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "1");
		} else {
			String index = (maxId + "").substring((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "").length());
			id = Integer.parseInt((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}
		OfficeSupply officeSupply = new OfficeSupply(
				id,
				addOfficeSupplyDTO.getInventory(),
				addOfficeSupplyDTO.getName(),
				addOfficeSupplyDTO.getTotal_inventory(),
				addOfficeSupplyDTO.getDescription(),
				addOfficeSupplyDTO.getDepartment_id()
		);
		officeSupplyDModel.add(officeSupply);
	}


	public void deleteOfficeSupply(int id) throws NotExistException {
		OfficeSupply officeSupply = officeSupplyDModel.getById(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		officeSupplyDModel.delete(id);
	}

	public OfficeSupply getOfficeSupply(int id) throws NotExistException {
		OfficeSupply officeSupply = officeSupplyDModel.getById(id);
		if (officeSupply == null){
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		return officeSupply;
	}

	public void updateOfficeSupply(UpdateOfficeSupplyDTO updateOfficeSupplyDTO)
			throws NotExistException, DuplicateOfficeSupplyException{
		int id = updateOfficeSupplyDTO.getId();
		int index = updateOfficeSupplyDTO.getIndex();
		String input = updateOfficeSupplyDTO.getInput();
		OfficeSupply officeSupply = officeSupplyDModel.getById(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				for (OfficeSupply e : officeSupplyDModel.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateOfficeSupplyException();
					}
				}
				officeSupply.setName(input);
				officeSupplyDModel.update(officeSupply);
				break;
			case 2:
				officeSupply.setDescription(input);
				officeSupplyDModel.update(officeSupply);
				break;
			case 3:
				officeSupply.setInventory(Integer.parseInt(input));
				officeSupplyDModel.update(officeSupply);
				break;
		}
    }

	public List<OfficeSupply> getAllOfficeSupplies() {
		return officeSupplyDModel.getAll();
	}

	public int getTotalInventory() {
		return officeSupplyDModel.getTotalInventory();
	}
}
