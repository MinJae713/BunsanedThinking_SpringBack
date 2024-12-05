package com.example.bunsanedthinking_springback.model.service.employee.compensationPlanning;

import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.AddPartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.UpdatePartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyResponse;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.serial.Serial;
import com.example.bunsanedthinking_springback.global.constants.service.employee.compensationPlanning.CompensationPlanningConstants;
import com.example.bunsanedthinking_springback.global.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.partnerCompany.PartnerCompanyEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompensationPlanningService {

	@Autowired
	private Serial serial;

	@Autowired
	private PartnerCompanyEntityModel partnerCompanyEntityModel;

	public void addPartnerCompany(AddPartnerCompanyRequest partnerCompanyDTO) throws DuplicatePartnerCompanyException {
		String name = partnerCompanyDTO.getName();
		String phoneNumber = partnerCompanyDTO.getPhoneNumber();
		PartnerCompanyType partnerCompanyType = PartnerCompanyType.indexOf(partnerCompanyDTO.getPartnerCompanyType());
		String headName = partnerCompanyDTO.getHeadName();
		String headPhoneNumber = partnerCompanyDTO.getHeadPhoneNumber();

		List<PartnerCompany> partnerCompanies = partnerCompanyEntityModel.getAll();
		for (PartnerCompany partnerCompany : partnerCompanies)
			if (partnerCompany.getName().equals(name))
				throw new DuplicatePartnerCompanyException();
		int id = NextIdGetter.getNextId(partnerCompanyEntityModel.getMaxId(), serial.getPartnercompany());
		PartnerCompany partnerCompany = new PartnerCompany(name, phoneNumber,
				partnerCompanyType, headName, headPhoneNumber);
		partnerCompany.setId(id);
		partnerCompany.setEvaluation(0); // 임의 지정
		partnerCompany.setReportList(new ArrayList<Report>()); // 임의 지정
		partnerCompanyEntityModel.add(partnerCompany);
	}

	public void evaluatePartnerCompany(int evaluate, int partnerCompanyId) throws NotExistException {
		PartnerCompany partnerCompany = getPartnerCompanyById(partnerCompanyId);
		partnerCompany.setEvaluation(evaluate);
		partnerCompanyEntityModel.update(partnerCompany);
	}

	public PartnerCompany getPartnerCompanyById(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyEntityModel.getById(id);
		if (partnerCompany == null) throw new NotExistException(CompensationPlanningConstants.PARTNER_COMPANY_NULL);
		return partnerCompany;
	}

	public PartnerCompanyDetailResponse getPartnerCompanyDetailById(int id) throws NotExistException {
		PartnerCompany partnerCompany = getPartnerCompanyById(id);
		if (partnerCompany == null) throw new NotExistException(CompensationPlanningConstants.PARTNER_COMPANY_NULL);
		return PartnerCompanyDetailResponse.of(partnerCompany);
	}

	public PartnerCompanyResponse getPartnerCompanyRowById(int id) throws NotExistException {
		return PartnerCompanyResponse.of(getPartnerCompanyById(id));
	}

	public void updatePartnerCompany(UpdatePartnerCompanyRequest partnerCompanyDTO)
		throws DuplicatePartnerCompanyException, NotExistException, RuntimeException {
		int index = partnerCompanyDTO.getIndex();
		String input = partnerCompanyDTO.getInput();
		int partnerCompanyId = partnerCompanyDTO.getPartnerCompanyId();

		PartnerCompany partnerCompany = getPartnerCompanyById(partnerCompanyId);
		if (partnerCompany == null) throw new NotExistException(CompensationPlanningConstants.PARTNER_COMPANY_NULL);
		switch (index) {
			case 1:
				for (PartnerCompany e : partnerCompanyEntityModel.getAll())
					if (e.getName().equals(input))
						throw new DuplicatePartnerCompanyException();
				if (!input.matches(CommonConstants.NAME_PATTERN_REGEXP))
					throw new RuntimeException(CompensationPlanningConstants.INVALID_NAME_FORMAT);
				partnerCompany.setName(input);
				partnerCompanyEntityModel.update(partnerCompany);
			case 2:
				if (!input.matches(CommonConstants.PHONE_NUMBER_PATTERN_REGEXP))
					throw new RuntimeException(CompensationPlanningConstants.INVALID_PHONE_FORMAT);
				partnerCompany.setPhoneNumber(input);
				partnerCompanyEntityModel.update(partnerCompany);
			case 3:
				if (!input.matches(CommonConstants.NAME_PATTERN_REGEXP))
					throw new RuntimeException(CompensationPlanningConstants.INVALID_NAME_FORMAT);
				partnerCompany.setHeadName(input);
				partnerCompanyEntityModel.update(partnerCompany);
				break;
			case 4:
				if (!input.matches(CommonConstants.PHONE_NUMBER_PATTERN_REGEXP))
					throw new RuntimeException(CompensationPlanningConstants.INVALID_PHONE_FORMAT);
				partnerCompany.setHeadPhoneNumber(input);
				partnerCompanyEntityModel.update(partnerCompany);
				break;
			default:
				break;
		}
	}

	public void deletePartnerCompany(int id) throws NotExistException {
		getPartnerCompanyById(id);
		partnerCompanyEntityModel.delete(id);
	}

	public List<PartnerCompanyResponse> getAll() {
		return partnerCompanyEntityModel.getAll().stream()
				.map(PartnerCompanyResponse::of)
				.collect(Collectors.toList());
	}
}
