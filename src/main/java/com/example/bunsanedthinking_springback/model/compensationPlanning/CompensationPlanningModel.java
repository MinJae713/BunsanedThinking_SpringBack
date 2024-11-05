package com.example.bunsanedthinking_springback.model.compensationPlanning;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.exception.DuplicatePartnerCompanyException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompensationPlanningModel {

	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;

	public void addPartnerCompany(String name, String phoneNumber, int partnerCompanyType, String headName, String headPhoneNumber) throws DuplicatePartnerCompanyException {
		for (PartnerCompanyVO partnerCompany : partnerCompanyMapper.getAll_CompensationPlanning()) {
			if (partnerCompany.getName().equals(name)) {
				throw new DuplicatePartnerCompanyException();
			}
		}
		Integer maxId = partnerCompanyMapper.getMaxId_CompensationPlanning();
		int id;
		if (maxId == null) {
			String compound = PartnerCompany.PARTNER_COMPANY_SERIAL_NUMBER + "1";
			id = Integer.parseInt(compound);
		} else {
			int partnerCompanySerialLength = (PartnerCompany.PARTNER_COMPANY_SERIAL_NUMBER + "").length();
			int index = Integer.parseInt(maxId.toString().substring(partnerCompanySerialLength));
			String compound = PartnerCompany.PARTNER_COMPANY_SERIAL_NUMBER + "" +(index+1);
			id = Integer.parseInt(compound);
		}
		PartnerCompanyVO partnerCompanyVO = new PartnerCompanyVO(id, headName, headPhoneNumber,
			4, name, partnerCompanyType, phoneNumber);
		partnerCompanyMapper.insert_CompensationPlanning(partnerCompanyVO);
	}

	public void evaluatePartnerCompany(int evaluate, int partnerCompanyId) throws NotExistException {

		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_CustomerSupport(partnerCompanyId)
				.orElseThrow(() -> new NotExistException("해당하는 협력업체 정보가 존재하지 않습니다."));
		partnerCompanyVO.setEvaluation(evaluate);
		partnerCompanyMapper.update_CompensationPlanning(partnerCompanyVO);
	}

	public PartnerCompany getPartnerCompany(int id) throws NotExistException{
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_CustomerSupport(id)
			.orElseThrow(() -> new NotExistException("해당하는 협력업체 정보가 존재하지 않습니다."));
		return new PartnerCompany(partnerCompanyVO.getEvaluation(), partnerCompanyVO.getHead_name(),
			partnerCompanyVO.getHead_phone_number(), partnerCompanyVO.getId(), partnerCompanyVO.getName(),
			PartnerCompanyType.indexOf(partnerCompanyVO.getPartner_company_type()),
			partnerCompanyVO.getPhone_number(), null);
	}

	public void updatePartnerCompany(int index, String input, int partnerCompanyId) throws DuplicatePartnerCompanyException, NotExistException{
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_CustomerSupport(partnerCompanyId)
			.orElseThrow(() -> new NotExistException("해당하는 협력업체 정보가 존재하지 않습니다."));
		switch (index) {
			case 1 -> {
				for (PartnerCompanyVO e : partnerCompanyMapper.getAll_CompensationPlanning())
					if (e.getName().equals(input)) throw new DuplicatePartnerCompanyException();
				partnerCompanyVO.setName(input);
			}
			case 2 -> partnerCompanyVO.setPhone_number(input);
			case 3 -> partnerCompanyVO.setHead_name(input);
			case 4 -> partnerCompanyVO.setHead_phone_number(input);
			default -> {
				return;
			}
		}
		partnerCompanyMapper.update_CompensationPlanning(partnerCompanyVO);
	}

	public void deletePartnerCompany(int id) throws NotExistException {
		partnerCompanyMapper.delete_CompensationPlanning(id);
	}

	public ArrayList<PartnerCompany> getAll() {
		ArrayList<PartnerCompany> result = new ArrayList<>();
		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyMapper.getAll_CompensationPlanning()) {
			result.add(new PartnerCompany(partnerCompanyVO.getEvaluation(), partnerCompanyVO.getHead_name(),
				partnerCompanyVO.getHead_phone_number(), partnerCompanyVO.getId(), partnerCompanyVO.getName(),
				PartnerCompanyType.indexOf(partnerCompanyVO.getPartner_company_type()),
				partnerCompanyVO.getPhone_number(), null));
		}
		return result;
	}
}
