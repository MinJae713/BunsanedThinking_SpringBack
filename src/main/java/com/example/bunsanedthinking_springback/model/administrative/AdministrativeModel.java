package com.example.bunsanedthinking_springback.model.administrative;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupplyList;
import com.example.bunsanedthinking_springback.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdministrativeModel {

	public void addOfficeSupply(String name, String explain, int inventory, OfficeSupplyList officeSupplyList) throws DuplicateOfficeSupplyException {
		for (OfficeSupply officeSupply : officeSupplyList.getAll()) {
			if (officeSupply.getName().equals(name)) {
				throw new DuplicateOfficeSupplyException();
			}
		}
		OfficeSupply officeSupply = new OfficeSupply(name, explain, inventory);
		officeSupplyList.add(officeSupply);
		
	}

	public void deleteOfficeSupply(OfficeSupplyList officeSupplyList, int id) throws NotExistException{
		try {
			officeSupplyList.delete(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
	}

	public OfficeSupply getOfficeSupply(OfficeSupplyList officeSupplyList, int id) throws NotExistException {
		try {
			return officeSupplyList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
	}

	public void updateDepartment(int index, String input, OfficeSupply officeSupply,
			OfficeSupplyList officeSupplyList) throws DuplicateOfficeSupplyException, NotExistException {
		try {
			switch (index) {
			case 1:
				for (OfficeSupply e : officeSupplyList.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateOfficeSupplyException();
					}
				}
				officeSupply.setName(input);
				officeSupplyList.update(officeSupply);
				break;
			case 2:
				officeSupply.setDescription(input);
				officeSupplyList.update(officeSupply);
				break;
			case 3:
				officeSupply.setInventory(Integer.parseInt(input));
				officeSupplyList.update(officeSupply);
				break;
			default:
				break;
			}
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 집기 비품 정보가 존재하지 않습니다.");
		}
		
	}
	// 메소드는 아래에 적어주셔유! (MVC 적용)
	public ArrayList<OfficeSupply> getAll(OfficeSupplyList officeSupplyList) {
		return officeSupplyList.getAll();
	}

	public int getTotalInventory(OfficeSupplyList officeSupplyList) {
		return officeSupplyList.getTotalInventory();
	}
}