package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.AddPartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.UpdatePartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyResponse;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.global.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.compensationPlanning.CompensationPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/compensationPlanning")
public class CompensationPlanningController {
	@Autowired
	private CompensationPlanningService compensationPlanningSModel;
	@PostMapping("/addPartnerCompany")
	public void addPartnerCompany(@RequestBody AddPartnerCompanyRequest partnerCompanyDTO)
		throws DuplicatePartnerCompanyException {
		/*
		{
			"name": "삼성전자",
			"phoneNumber": "010-3737-2855",
			"partnerCompanyType": 1,
			"headName": "김대현",
			"headPhoneNumber": "010-1111-2222"
		}
		 */
		compensationPlanningSModel.addPartnerCompany(partnerCompanyDTO);
	}

	@PatchMapping("/evaluatePartnerCompany")
	public void evaluatePartnerCompany(@RequestParam int evaluate, @RequestParam int partnerCompanyId) throws NotExistException{
		compensationPlanningSModel.evaluatePartnerCompany(evaluate, partnerCompanyId);
	}

	@GetMapping("/getPartnerCompanyById")
	public PartnerCompany getPartnerCompanyById(@RequestParam int id) throws NotExistException {
		return compensationPlanningSModel.getPartnerCompanyById(id);
	}

	@GetMapping("/getPartnerCompanyRowById")
	public PartnerCompanyResponse getPartnerCompanyRowById(@RequestParam int id) throws NotExistException {
		return compensationPlanningSModel.getPartnerCompanyRowById(id);
	}

	@PatchMapping("/updatePartnerCompany")
	public void updatePartnerCompany(@RequestBody UpdatePartnerCompanyRequest partnerCompanyDTO)
		throws DuplicatePartnerCompanyException, NotExistException {
		/*
		{
			"index": 2,
			"input": "010-5678-3456",
			"partnerCompanyId": 3001
		}
		 */
		compensationPlanningSModel.updatePartnerCompany(partnerCompanyDTO);
	}

	@DeleteMapping("/deletePartnerCompany")
	public void deletePartnerCompany(int id) throws NotExistException {
		compensationPlanningSModel.deletePartnerCompany(id);
	}

	@GetMapping("/getAll")
	public List<PartnerCompanyResponse> getAll() {
		return compensationPlanningSModel.getAll();
	}
	// get은 검증 완
}
