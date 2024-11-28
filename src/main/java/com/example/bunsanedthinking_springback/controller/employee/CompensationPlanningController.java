package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.AddPartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.UpdatePartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyResponse;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.global.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.compensationPlanning.CompensationPlanningService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/employee/compensationPlanning")
public class CompensationPlanningController {
	@Autowired
	private CompensationPlanningService compensationPlanningSModel;
	@PostMapping("/addPartnerCompany")
	public void addPartnerCompany(@RequestBody @Valid AddPartnerCompanyRequest partnerCompanyDTO)
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
	public void evaluatePartnerCompany(@Range(min = 1, max = 5, message = "1에서 5중에 입력해주세요") @RequestParam int evaluate, @RequestParam int partnerCompanyId) throws NotExistException{
		compensationPlanningSModel.evaluatePartnerCompany(evaluate, partnerCompanyId);
	}

	@GetMapping("/getPartnerCompanyById")
	public PartnerCompany getPartnerCompanyById(@RequestParam int id) throws NotExistException {
		return compensationPlanningSModel.getPartnerCompanyById(id);
	}

	@GetMapping("/getPartnerCompanyDetailById")
	public PartnerCompanyDetailResponse getPartnerCompanyDetailById(@RequestParam int id) throws NotExistException {
		return compensationPlanningSModel.getPartnerCompanyDetailById(id);
	}

	@GetMapping("/getPartnerCompanyRowById")
	public PartnerCompanyResponse getPartnerCompanyRowById(@RequestParam int id) throws NotExistException {
		return compensationPlanningSModel.getPartnerCompanyRowById(id);
	}

	@PatchMapping("/updatePartnerCompany")
	public void updatePartnerCompany(@RequestBody @Valid UpdatePartnerCompanyRequest partnerCompanyDTO)
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
