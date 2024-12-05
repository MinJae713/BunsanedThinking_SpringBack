package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.administrative.request.AddOfficeSupplyRequest;
import com.example.bunsanedthinking_springback.dto.employee.administrative.request.UpdateOfficeSupplyRequest;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.global.exception.DuplicateOfficeSupplyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.administrative.AdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/administrative")
public class AdministrativeController {

	@Autowired
	private AdministrativeService administrativeSModel;

	@PostMapping("/addOfficeSupply")
	public void addOfficeSupply(@RequestBody AddOfficeSupplyRequest addOfficeSupplyRequest) throws DuplicateOfficeSupplyException {
		administrativeSModel.addOfficeSupply(addOfficeSupplyRequest);
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
	public void updateOfficeSupply(@RequestBody UpdateOfficeSupplyRequest updateOfficeSupplyRequest)
			throws NotExistException, DuplicateOfficeSupplyException {
		administrativeSModel.updateOfficeSupply(updateOfficeSupplyRequest);
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
