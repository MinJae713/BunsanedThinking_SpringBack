package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.mo.AddOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupplyList;
import com.example.bunsanedthinking_springback.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.administrative.AdministrativeModel;
import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee/administrative")
public class AdministrativeController {

	@Autowired
	private AdministrativeModel administrativeModel;

	@PostMapping("/addOfficeSupply")
	public void addOfficeSupply(@RequestBody AddOfficeSupplyDTO addOfficeSupplyDTO) throws DuplicateOfficeSupplyException {
		administrativeModel.addOfficeSupply(
				addOfficeSupplyDTO.getName(),
				addOfficeSupplyDTO.getDescription(),
				addOfficeSupplyDTO.getInventory(),
				addOfficeSupplyDTO.getTotal_inventory(),
				addOfficeSupplyDTO.getDepartment_id());
	}

	@DeleteMapping("/deleteOfficeSupply")
	public void deleteOfficeSupply(@RequestParam int id) throws NotExistException {
		administrativeModel.deleteOfficeSupply(id);
	}

	@GetMapping("/getOfficeSupply")
	public OfficeSupplyVO getOfficeSupply(@RequestParam int id) throws NotExistException{
		return administrativeModel.getOfficeSupply(id);
	}

	@PatchMapping("/updateOfficeSupply")
	public void updateOfficeSupply(@RequestParam int index, @RequestParam String input,
								   @RequestParam int id) throws NotExistException {
		administrativeModel.updateOfficeSupply(index, input, id);
	}

	@GetMapping("/getAll")
	public List<OfficeSupplyVO> getAll() {
		return administrativeModel.getAllOfficeSupplies();
	}

	@GetMapping("/getTotalInventory")
	public int getTotalInventory() {
		return administrativeModel.getTotalInventory();
	}
}
