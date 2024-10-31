package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupplyList;
import com.example.bunsanedthinking_springback.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.administrative.AdministrativeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/administrative")
public class AdministrativeController {
	@Autowired
	private AdministrativeModel administrativeModel;

	public void addOfficeSupply(String name, String explain, int inventory, OfficeSupplyList officeSupplyList) throws DuplicateOfficeSupplyException {
		administrativeModel.addOfficeSupply(name, explain, inventory, officeSupplyList);
	}

	public void deleteOfficeSupply(OfficeSupplyList officeSupplyList, int id) throws NotExistException {
		administrativeModel.deleteOfficeSupply(officeSupplyList, id);
	}
	public OfficeSupply getOfficeSupply(OfficeSupplyList officeSupplyList, int id) throws NotExistException{
		return administrativeModel.getOfficeSupply(officeSupplyList, id);
	}
	public void updateDepartment(int index, String input, OfficeSupply officeSupply,
			OfficeSupplyList officeSupplyList) throws DuplicateOfficeSupplyException, NotExistException {
		administrativeModel.updateDepartment(index, input, officeSupply, officeSupplyList);
	}
	public ArrayList<OfficeSupply> getAll(OfficeSupplyList officeSupplyList) {
		return administrativeModel.getAll(officeSupplyList);
	}
	public int getTotalInventory(OfficeSupplyList officeSupplyList) {
		return administrativeModel.getTotalInventory(officeSupplyList);
	}
}
