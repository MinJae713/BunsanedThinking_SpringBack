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

	public void addOfficeSupply(String name, String description, int inventory) throws DuplicateOfficeSupplyException {
		administrativeModel.addOfficeSupply(name, description, inventory);
	}

	public void deleteOfficeSupply(int id) throws NotExistException {
		administrativeModel.deleteOfficeSupply(id);
	}
	public OfficeSupply getOfficeSupply(int id) throws NotExistException{
		return administrativeModel.getOfficeSupply(id);
	}
	public void updateOfficeSupply(int index, String input, int id) throws NotExistException {
		administrativeModel.updateOfficeSupply(index, input, id);
	}

	public ArrayList<OfficeSupply> getAll() {
		return administrativeModel.getAllOfficeSupplies();
	}
	public int getTotalInventory() {
		return administrativeModel.getTotalInventory();
	}
}
