package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.yoo.AddPartnerCompanyDTO;
import com.example.bunsanedthinking_springback.dto.yoo.UpdatePartnerCompanyDTO;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.global.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.compensationPlanning.CompensationPlanningModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/compensationPlanning")
public class CompensationPlanningController {
	@Autowired
	private CompensationPlanningModel compensationPlanningModel;

	//	public void addPartnerCompany(String name, String phoneNumber, PartnerCompanyType partnerCompanyType, String headName, String headPhoneNumber, PartnerCompanyList partnerCompanyList) throws DuplicatePartnerCompanyException {
	//		compensationPlanningModel.addPartnerCompany(name, phoneNumber, partnerCompanyType, headName, headPhoneNumber, partnerCompanyList);
	//	}
	//	public void evaluatePartnerCompany(int evaluate, PartnerCompany partnerCompany, PartnerCompanyList partnerCompanyList) throws NotExistException{
	//		compensationPlanningModel.evaluatePartnerCompany(evaluate, partnerCompany, partnerCompanyList);
	//	}
	//	public PartnerCompany getPartnerCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
	//		return compensationPlanningModel.getPartnerCompany(partnerCompanyList, id);
	//	}
	//	public void updatePartnerCompany(int index, String input, PartnerCompany partnerCompany, PartnerCompanyList partnerCompanyList) throws DuplicatePartnerCompanyException, NotExistException{
	//		compensationPlanningModel.updatePartnerCompany(index, input, partnerCompany, partnerCompanyList);
	//	}
	//	public void deletePartnerCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
	//		compensationPlanningModel.deletePartnerCompany(partnerCompanyList, id);
	//	}
	//	public ArrayList<PartnerCompany> getAll(PartnerCompanyList partnerCompanyList) {
	//		return compensationPlanningModel.getAll(partnerCompanyList);
	//	}
	@PostMapping("/addPartnerCompany")
	public void addPartnerCompany(@RequestBody AddPartnerCompanyDTO partnerCompanyDTO)
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
		compensationPlanningModel.addPartnerCompany(partnerCompanyDTO);
	}

	@PatchMapping("/evaluatePartnerCompany")
	public void evaluatePartnerCompany(@RequestParam int evaluate, @RequestParam int partnerCompanyId) throws
		NotExistException {
		compensationPlanningModel.evaluatePartnerCompany(evaluate, partnerCompanyId);
	}

	@GetMapping("/getPartnerCompany")
	public PartnerCompany getPartnerCompany(@RequestParam int id) throws NotExistException {
		return compensationPlanningModel.getPartnerCompany(id);
	}

	@PatchMapping("/updatePartnerCompany")
	public void updatePartnerCompany(@RequestBody UpdatePartnerCompanyDTO partnerCompanyDTO)
		throws DuplicatePartnerCompanyException, NotExistException {
		/*
		{
			"index": 2,
			"input": "010-5678-3456",
			"partnerCompanyId": 3001
		}
		 */
		compensationPlanningModel.updatePartnerCompany(partnerCompanyDTO);
	}

	@DeleteMapping("/deletePartnerCompany")
	public void deletePartnerCompany(int id) throws NotExistException {
		compensationPlanningModel.deletePartnerCompany(id);
	}

	@GetMapping("/getAll")
	public ArrayList<PartnerCompany> getAll() {
		return compensationPlanningModel.getAll();
	}
}
