package com.example.bunsanedthinking_springback.model.service.employee.administrative;

import com.example.bunsanedthinking_springback.dto.employee.administrative.AddOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.dto.employee.administrative.UpdateOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.global.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.officeSupply.OfficeSupplyEntityModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrativeService {

	@Autowired
	private OfficeSupplyEntityModel officeSupplyEntityModel;

	@Value("${serials.officesupply}")
	public int OFFICESUPPLY_SERIAL_NUMBER;

	public void addOfficeSupply(AddOfficeSupplyDTO addOfficeSupplyDTO) throws DuplicateOfficeSupplyException {
		boolean isExistOfficeSupplyName = officeSupplyEntityModel.getAll().stream()
				.anyMatch(officeSupply -> officeSupply.getName().equals(addOfficeSupplyDTO.getName()));

		if (isExistOfficeSupplyName) {
			throw new DuplicateOfficeSupplyException();
		}
		// 현재 최대 ID를 가져와서 NextIdGetter를 사용하여 새로운 ID 생성
		Integer maxId = officeSupplyEntityModel.getMaxId();
		int id = NextIdGetter.getNextId(maxId, OFFICESUPPLY_SERIAL_NUMBER);

		OfficeSupply officeSupply = new OfficeSupply(
				id,
				addOfficeSupplyDTO.getInventory(),
				addOfficeSupplyDTO.getName(),
				addOfficeSupplyDTO.getTotal_inventory(),
				addOfficeSupplyDTO.getDescription(),
				addOfficeSupplyDTO.getDepartment_id()
		);
		officeSupplyEntityModel.add(officeSupply);
	}

	public void deleteOfficeSupply(int id) throws NotExistException {
		OfficeSupply officeSupply = officeSupplyEntityModel.getById(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		officeSupplyEntityModel.delete(id);
	}

	public OfficeSupply getOfficeSupply(int id) throws NotExistException {
		OfficeSupply officeSupply = officeSupplyEntityModel.getById(id);
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
		OfficeSupply officeSupply = officeSupplyEntityModel.getById(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				for (OfficeSupply e : officeSupplyEntityModel.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateOfficeSupplyException();
					}
				}
				officeSupply.setName(input);
				officeSupplyEntityModel.update(officeSupply);
				break;
			case 2:
				officeSupply.setDescription(input);
				officeSupplyEntityModel.update(officeSupply);
				break;
			case 3:
				officeSupply.setInventory(Integer.parseInt(input));
				officeSupplyEntityModel.update(officeSupply);
				break;
		}
    }

	public List<OfficeSupply> getAllOfficeSupplies() {
		return officeSupplyEntityModel.getAll();
	}

	public int getTotalInventory() {
		return officeSupplyEntityModel.getTotalInventory();
	}
}
