package com.example.bunsanedthinking_springback.model.administrative;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupplyList;
import com.example.bunsanedthinking_springback.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.OfficeSupplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdministrativeModel {

	@Autowired
	private OfficeSupplyMapper officeSupplyMapper;

	public void addOfficeSupply(String name, String description, int inventory) throws DuplicateOfficeSupplyException {
		OfficeSupply existingSupply = officeSupplyMapper.findByName_OfficeSupply(name);
		if (existingSupply != null) {
			throw new DuplicateOfficeSupplyException();
		}
		OfficeSupply officeSupply = new OfficeSupply(name, description, inventory);
		officeSupplyMapper.insert_OfficeSupply(officeSupply);
	}

	public void deleteOfficeSupply(int id) throws NotExistException{
		OfficeSupply officeSupply = officeSupplyMapper.findById_OfficeSupply(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		officeSupplyMapper.delete_OfficeSupply(id);
	}

	public OfficeSupply getOfficeSupply(int id) throws NotExistException {
		OfficeSupply officeSupply = officeSupplyMapper.findById_OfficeSupply(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		return officeSupply;
	}

	public void updateOfficeSupply(int index, String input, int id) throws NotExistException {
		OfficeSupply officeSupply = getOfficeSupply(id);
		if (officeSupply == null) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				officeSupply.setName(input);
				break;
			case 2:
				officeSupply.setDescription(input);
				break;
			case 3: 
				officeSupply.setInventory(Integer.parseInt(input));
				break;
			default:
				throw new IllegalArgumentException("잘못된 선택입니다. 다시 선택해주세요.");
		}
		officeSupplyMapper.update_OfficeSupply(officeSupply);
	}
	// 메소드는 아래에 적어주셔유! (MVC 적용)
	public ArrayList<OfficeSupply> getAllOfficeSupplies() {
		return new ArrayList<>(officeSupplyMapper.getAll_OfficeSupply());
	}

	public int getTotalInventory() {
		return officeSupplyMapper.getTotalInventory_OfficeSupply();
	}
}