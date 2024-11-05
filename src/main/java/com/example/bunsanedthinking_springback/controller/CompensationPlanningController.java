package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
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
	public void addPartnerCompany(@RequestParam String name,
								  @RequestParam String phoneNumber,
								  @RequestParam int partnerCompanyType,
								  @RequestParam String headName,
								  @RequestParam String headPhoneNumber)
				throws DuplicatePartnerCompanyException {
		compensationPlanningModel.addPartnerCompany(name, phoneNumber, partnerCompanyType, headName, headPhoneNumber);
	}
	@PatchMapping("/evaluatePartnerCompany")
	public void evaluatePartnerCompany(int evaluate, int partnerCompanyId) throws NotExistException{
		compensationPlanningModel.evaluatePartnerCompany(evaluate, partnerCompanyId);
	}
	@GetMapping("/getPartnerCompany")
	public PartnerCompany getPartnerCompany(@RequestParam int id) throws NotExistException {
		return compensationPlanningModel.getPartnerCompany(id);
	}
	@PatchMapping("/updatePartnerCompany")
	public void updatePartnerCompany(@RequestParam int index,
									 @RequestParam String input,
									 @RequestParam int partnerCompanyId)
				throws DuplicatePartnerCompanyException, NotExistException{
		compensationPlanningModel.updatePartnerCompany(index, input, partnerCompanyId);
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
