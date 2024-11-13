package com.example.bunsanedthinking_springback.model.service.employee.compensationPlanning;

import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.AddPartnerCompanyDTO;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.UpdatePartnerCompanyDTO;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.partnerCompany.PartnerCompanyEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompensationPlanningService {
	@Autowired
	private PartnerCompanyEntityModel partnerCompanyEntityModel;

	@Value("${serials.partnercompany}")
	public Integer PARTNER_COMPANY_SERIAL_NUMBER;

	public void addPartnerCompany(AddPartnerCompanyDTO partnerCompanyDTO) throws DuplicatePartnerCompanyException {
		String name = partnerCompanyDTO.getName();
		String phoneNumber = partnerCompanyDTO.getPhoneNumber();
		PartnerCompanyType partnerCompanyType = PartnerCompanyType.values()[partnerCompanyDTO.getPartnerCompanyType()];
		String headName = partnerCompanyDTO.getHeadName();
		String headPhoneNumber = partnerCompanyDTO.getHeadPhoneNumber();

		List<PartnerCompany> partnerCompanies = getAll();
		for (PartnerCompany partnerCompany : partnerCompanies)
			if (partnerCompany.getName().equals(name))
				throw new DuplicatePartnerCompanyException();
		int id = NextIdGetter.getNextId(partnerCompanyEntityModel.getMaxId(), PARTNER_COMPANY_SERIAL_NUMBER);
		// evaluation, reportList 지정X
		PartnerCompany partnerCompany = new PartnerCompany(name, phoneNumber,
				partnerCompanyType, headName, headPhoneNumber);
		partnerCompany.setId(id);
		partnerCompany.setEvaluation(0); // 임의 지정
		partnerCompany.setReportList(new ArrayList<Report>()); // 임의 지정
		partnerCompanyEntityModel.add(partnerCompany);

//		int partnerCompanySerialLength = (PartnerCompany.PARTNER_COMPANY_SERIAL_NUMBER + "").length();
//		int index = Integer.parseInt(maxId.toString().substring(partnerCompanySerialLength));
//		String compound = PartnerCompany.PARTNER_COMPANY_SERIAL_NUMBER + "" + (index + 1);
//		id = Integer.parseInt(compound);
	}

	public void evaluatePartnerCompany(int evaluate, int partnerCompanyId) throws NotExistException {
		PartnerCompany partnerCompany = getPartnerCompany(partnerCompanyId);
		if (partnerCompany == null) return;
		partnerCompany.setEvaluation(evaluate);
		partnerCompanyEntityModel.update(partnerCompany);
	}

	public PartnerCompany getPartnerCompany(int id) throws NotExistException {
		return partnerCompanyEntityModel.getById(id);
	}

	public void updatePartnerCompany(UpdatePartnerCompanyDTO partnerCompanyDTO)
		throws DuplicatePartnerCompanyException, NotExistException {
		int index = partnerCompanyDTO.getIndex();
		String input = partnerCompanyDTO.getInput();
		int partnerCompanyId = partnerCompanyDTO.getPartnerCompanyId();

		PartnerCompany partnerCompany = getPartnerCompany(partnerCompanyId);
		if (partnerCompany == null) return;
		switch (index) {
			case 1:
				for (PartnerCompany e : partnerCompanyEntityModel.getAll())
					if (e.getName().equals(input))
						throw new DuplicatePartnerCompanyException();
				partnerCompany.setName(input);
				partnerCompanyEntityModel.update(partnerCompany);
			case 2:
				partnerCompany.setPhoneNumber(input);
				partnerCompanyEntityModel.update(partnerCompany);
			case 3:
				partnerCompany.setHeadName(input);
				partnerCompanyEntityModel.update(partnerCompany);
				break;
			case 4:
				partnerCompany.setHeadPhoneNumber(input);
				partnerCompanyEntityModel.update(partnerCompany);
				break;
			default:
				break;
		}
	}

	public void deletePartnerCompany(int id) throws NotExistException {
		partnerCompanyEntityModel.delete(id);
	}

	public List<PartnerCompany> getAll() {
		return partnerCompanyEntityModel.getAll();
	}
}
