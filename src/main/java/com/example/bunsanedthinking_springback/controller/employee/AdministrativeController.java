package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.administrative.AddOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.dto.mo.UpdateOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.global.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.administrative.AdministrativeSModel;
import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/administrative")
public class AdministrativeController {

	@Autowired
	private AdministrativeSModel administrativeSModel;

//	@PostMapping("/addOfficeSupply")
//	public void addOfficeSupply(@RequestBody AddOfficeSupplyDTO addOfficeSupplyDTO) throws DuplicateOfficeSupplyException {
//		administrativeSModel.addOfficeSupply(
//				addOfficeSupplyDTO.getName(),
//				addOfficeSupplyDTO.getDescription(),
//				addOfficeSupplyDTO.getInventory(),
//				addOfficeSupplyDTO.getTotal_inventory(),
//				addOfficeSupplyDTO.getDepartment_id());
//	}
	@PostMapping("/addOfficeSupply")
	public void addOfficeSupply(@RequestBody AddOfficeSupplyDTO addOfficeSupplyDTO) throws DuplicateOfficeSupplyException {
		administrativeSModel.addOfficeSupply(addOfficeSupplyDTO);
	}

	@DeleteMapping("/deleteOfficeSupply")
	public void deleteOfficeSupply(@RequestParam int id) throws NotExistException {
		administrativeSModel.deleteOfficeSupply(id);
	}

	@GetMapping("/getOfficeSupply")
	public OfficeSupply getOfficeSupply(@RequestParam int id) throws NotExistException{
		return administrativeSModel.getOfficeSupply(id);
	}

	@PatchMapping("/updateOfficeSupply")
	public void updateOfficeSupply(@RequestBody UpdateOfficeSupplyDTO updateOfficeSupplyDTO)
			throws NotExistException, DuplicateOfficeSupplyException {
		administrativeSModel.updateOfficeSupply(updateOfficeSupplyDTO);
	}

	@GetMapping("/getAll")
	public List<OfficeSupply> getAll() {
		return administrativeSModel.getAllOfficeSupplies();
	}

	@GetMapping("/getTotalInventory")
	public int getTotalInventory() {
		return administrativeSModel.getTotalInventory();
	}
}
