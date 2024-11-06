package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.administrative.AddOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
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

	@PostMapping("/addOfficeSupply")
	public void addOfficeSupply(@RequestBody AddOfficeSupplyDTO addOfficeSupplyDTO) throws DuplicateOfficeSupplyException {
		administrativeSModel.addOfficeSupply(
				addOfficeSupplyDTO.getName(),
				addOfficeSupplyDTO.getDescription(),
				addOfficeSupplyDTO.getInventory(),
				addOfficeSupplyDTO.getTotal_inventory(),
				addOfficeSupplyDTO.getDepartment_id());
	}

	@DeleteMapping("/deleteOfficeSupply")
	public void deleteOfficeSupply(@RequestParam int id) throws NotExistException {
		administrativeSModel.deleteOfficeSupply(id);
	}

	@GetMapping("/getOfficeSupply")
	public OfficeSupplyVO getOfficeSupply(@RequestParam int id) throws NotExistException{
		return administrativeSModel.getOfficeSupply(id);
	}

	@PatchMapping("/updateOfficeSupply")
	public void updateOfficeSupply(@RequestParam int index, @RequestParam String input,
								   @RequestParam int id) throws NotExistException {
		administrativeSModel.updateOfficeSupply(index, input, id);
	}

	@GetMapping("/getAll")
	public List<OfficeSupplyVO> getAll() {
		return administrativeSModel.getAllOfficeSupplies();
	}

	@GetMapping("/getTotalInventory")
	public int getTotalInventory() {
		return administrativeSModel.getTotalInventory();
	}
}
