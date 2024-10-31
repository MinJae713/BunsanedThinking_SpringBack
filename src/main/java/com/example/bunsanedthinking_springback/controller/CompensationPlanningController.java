package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.compensationPlanning.CompensationPlanningModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/compensationPlanning")
public class CompensationPlanningController {
	@Autowired
	private CompensationPlanningModel compensationPlanningModel;

	public void addPartnerCompany(String name, String phoneNumber, PartnerCompanyType partnerCompanyType, String headName, String headPhoneNumber, PartnerCompanyList partnerCompanyList) throws DuplicatePartnerCompanyException {
		compensationPlanningModel.addPartnerCompany(name, phoneNumber, partnerCompanyType, headName, headPhoneNumber, partnerCompanyList);
	}
	public void evaluatePartnerCompany(int evaluate, PartnerCompany partnerCompany, PartnerCompanyList partnerCompanyList) throws NotExistException{
		compensationPlanningModel.evaluatePartnerCompany(evaluate, partnerCompany, partnerCompanyList);
	}
	public PartnerCompany getPartnerCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return compensationPlanningModel.getPartnerCompany(partnerCompanyList, id);
	}
	public void updatePartnerCompany(int index, String input, PartnerCompany partnerCompany, PartnerCompanyList partnerCompanyList) throws DuplicatePartnerCompanyException, NotExistException{
		compensationPlanningModel.updatePartnerCompany(index, input, partnerCompany, partnerCompanyList);
	}
	public void deletePartnerCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		compensationPlanningModel.deletePartnerCompany(partnerCompanyList, id);
	}
	public ArrayList<PartnerCompany> getAll(PartnerCompanyList partnerCompanyList) {
		return compensationPlanningModel.getAll(partnerCompanyList);
	}
}
