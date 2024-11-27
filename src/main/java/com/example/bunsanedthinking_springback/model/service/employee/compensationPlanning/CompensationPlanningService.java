package com.example.bunsanedthinking_springback.model.service.employee.compensationPlanning;

import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.AddPartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.request.UpdatePartnerCompanyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensationPlanning.response.PartnerCompanyResponse;
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
import java.util.stream.Collectors;

@Service
public class CompensationPlanningService {
	@Autowired
	private PartnerCompanyEntityModel partnerCompanyEntityModel;

	@Value("${serials.partnercompany}")
	public Integer PARTNER_COMPANY_SERIAL_NUMBER;

	public void addPartnerCompany(AddPartnerCompanyRequest partnerCompanyDTO) throws DuplicatePartnerCompanyException {
		String name = partnerCompanyDTO.getName();
		String phoneNumber = partnerCompanyDTO.getPhoneNumber();
		PartnerCompanyType partnerCompanyType = PartnerCompanyType.values()[partnerCompanyDTO.getPartnerCompanyType()];
		String headName = partnerCompanyDTO.getHeadName();
		String headPhoneNumber = partnerCompanyDTO.getHeadPhoneNumber();

		List<PartnerCompany> partnerCompanies = partnerCompanyEntityModel.getAll();
		for (PartnerCompany partnerCompany : partnerCompanies)
			if (partnerCompany.getName().equals(name))
				throw new DuplicatePartnerCompanyException();
		int id = NextIdGetter.getNextId(partnerCompanyEntityModel.getMaxId(), PARTNER_COMPANY_SERIAL_NUMBER);
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
		if (partnerCompany == null) throw new NotExistException("해당 협력업체가 없습니다");
		return partnerCompany;
	}

	public PartnerCompanyDetailResponse getPartnerCompanyDetailById(int id) throws NotExistException {
		PartnerCompany partnerCompany = getPartnerCompanyById(id);
		if (partnerCompany == null) throw new NotExistException("아이디 해당 협력업체가 없습니다");
		return PartnerCompanyDetailResponse.of(partnerCompany);
	}

	public PartnerCompanyResponse getPartnerCompanyRowById(int id) throws NotExistException {
		return PartnerCompanyResponse.of(getPartnerCompanyById(id));
	}

	public void updatePartnerCompany(UpdatePartnerCompanyRequest partnerCompanyDTO)
		throws DuplicatePartnerCompanyException, NotExistException {
		int index = partnerCompanyDTO.getIndex();
		String input = partnerCompanyDTO.getInput();
		int partnerCompanyId = partnerCompanyDTO.getPartnerCompanyId();

		PartnerCompany partnerCompany = getPartnerCompanyById(partnerCompanyId);
		if (partnerCompany == null) throw new NotExistException("해당 협력업체가 없습니다");
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
		getPartnerCompanyById(id);
		partnerCompanyEntityModel.delete(id);
	}

	public List<PartnerCompanyResponse> getAll() {
		return partnerCompanyEntityModel.getAll().stream()
				.map(PartnerCompanyResponse::of)
				.collect(Collectors.toList());
	}
}
