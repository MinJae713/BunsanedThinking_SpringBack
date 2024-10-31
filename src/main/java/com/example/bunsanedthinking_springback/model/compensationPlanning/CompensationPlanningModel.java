package com.example.bunsanedthinking_springback.model.compensationPlanning;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompensationPlanningModel {

	public void addPartnerCompany(String name, String phoneNumber, PartnerCompanyType partnerCompanyType, String headName, String headPhoneNumber, PartnerCompanyList partnerCompanyList) throws DuplicatePartnerCompanyException {
		for (PartnerCompany partnerCompany : partnerCompanyList.getAll()) {
			if (partnerCompany.getName().equals(name)) {
				throw new DuplicatePartnerCompanyException();
			}
		}
		
		PartnerCompany PartnerCompany = new PartnerCompany(name, phoneNumber, partnerCompanyType, headName, headPhoneNumber);
		partnerCompanyList.add(PartnerCompany);
	}


	public void evaluatePartnerCompany(int evaluate, PartnerCompany partnerCompany, PartnerCompanyList partnerCompanyList) throws NotExistException {
		partnerCompany.setEvaluation(evaluate);
		partnerCompanyList.update(partnerCompany);
	}

	public PartnerCompany getPartnerCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException{
		try {
			return partnerCompanyList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 협력 업체 정보가 존재하지 않습니다.");
		}
	}

	public void updatePartnerCompany(int index, String input, PartnerCompany partnerCompany, PartnerCompanyList partnerCompanyList) throws DuplicatePartnerCompanyException, NotExistException{
		switch (index) {
		case 1:
			for (PartnerCompany e : partnerCompanyList.getAll()) {
				if (e.getName().equals(input)) {
					throw new DuplicatePartnerCompanyException();
				}
			}
			partnerCompany.setName(input);
			partnerCompanyList.update(partnerCompany);
			break;
		case 2:
			partnerCompany.setPhoneNumber(input);
			partnerCompanyList.update(partnerCompany);
			break;
		case 3:
			partnerCompany.setHeadName(input);
			partnerCompanyList.update(partnerCompany);
			break;
		case 4:
			partnerCompany.setHeadPhoneNumber(input);
			partnerCompanyList.update(partnerCompany);
			break;
		default:
			break;
		}
	}

	public void deletePartnerCompany(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		partnerCompanyList.delete(id);
	}
	public ArrayList<PartnerCompany> getAll(PartnerCompanyList partnerCompanyList) {
		return partnerCompanyList.getAll();
	}
}