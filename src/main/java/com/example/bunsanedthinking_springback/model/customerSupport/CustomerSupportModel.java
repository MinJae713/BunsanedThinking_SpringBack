package com.example.bunsanedthinking_springback.model.customerSupport;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintProcessStatus;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintType;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AccidentMapper;
import com.example.bunsanedthinking_springback.repository.ComplaintMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import com.example.bunsanedthinking_springback.vo.ComplaintVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import com.example.bunsanedthinking_springback.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerSupportModel {

	@Autowired
	private ComplaintMapper complaintMapper;
	@Autowired
	private AccidentMapper accidentMapper;
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;

	public void handleComplaint(String employeeName, int complaintId, String result) throws NotExistException, AlreadyProcessedException {
		ComplaintVO complaintVO = complaintMapper.findById_CustomerSupport(complaintId)
			.orElseThrow(() -> new NotExistException("해당하는 민원 정보를 찾을 수 없습니다."));
		if (complaintVO.getProcess_status() == ComplaintProcessStatus.Completed.ordinal()) {
			throw new AlreadyProcessedException("이미 민원 처리가 완료되었습니다.");
		}
		// 로그인한 직원 이름이 여기 드감
		complaintVO.setEmployee_name(employeeName);
		complaintVO.setResult(result);
		complaintVO.setProcessing_date(LocalDate.now());
		complaintVO.setProcess_status(ComplaintProcessStatus.Completed.ordinal());
		complaintMapper.update_CustomerSupport(complaintVO);
	}

	public void handleAccident(int accidentId, int damageAssessmentCompanyId,
							   int roadsideAssistanceCompanyId) throws AlreadyProcessedException {
		Optional<AccidentVO> optionalAccidentVO = accidentMapper.findByID_CustomerSupport(accidentId);
		if (optionalAccidentVO.isEmpty()) {
			return;
		}
		AccidentVO accidentVO = optionalAccidentVO.get();
		if (accidentVO.getProcess_status() == AccidentProcessStatus.Completed.ordinal()) {
			throw new AlreadyProcessedException("이미 신고 처리가 완료되었습니다.");
		} else if (accidentVO.getProcess_status() == AccidentProcessStatus.Processing.ordinal()) {
			throw new AlreadyProcessedException("신고 처리 중입니다.");
		}
		accidentVO.setProcess_status(AccidentProcessStatus.Processing.ordinal());
		accidentMapper.update_CustomerSupport(accidentVO);
		ReportVO reportVO = new ReportVO(accidentVO.getId(), null,
			ReportProcessStatus.Unprocessed.ordinal(), roadsideAssistanceCompanyId, damageAssessmentCompanyId);
		reportMapper.insert_CustomerSupport(reportVO);
	}
	public List<Complaint> getAllComplaint() {
		List<Complaint> result = new ArrayList<>();
		for (ComplaintVO complaintVO : complaintMapper.getAll_CustomerSupport()) {
			result.add(new Complaint(
				ComplaintType.indexOf(complaintVO.getComplaint_type()), complaintVO.getContent(), complaintVO.getCustomer_id(),
				complaintVO.getEmployee_name(), complaintVO.getId(), Date.valueOf(complaintVO.getDate()),
				Date.valueOf(complaintVO.getProcessing_date()), ComplaintProcessStatus.indexOf(complaintVO.getProcess_status()), complaintVO.getTitle(),
				complaintVO.getResult()));
		}
		return result;
	}
	public List<Complaint> getAllUnprocessedComplaint() {
		return getAllByProcessStatus(ComplaintProcessStatus.Unprocessed);
	}

	public List<Complaint> getAllProcessedComplaint() {
		return getAllByProcessStatus(ComplaintProcessStatus.Completed);
	}

	private List<Complaint> getAllByProcessStatus(ComplaintProcessStatus processStatus) {
		List<Complaint> result = new ArrayList<>();
		for (ComplaintVO complaintVO : complaintMapper.findByProcessStatus_CustomerSupport(processStatus.ordinal())) {
			result.add(new Complaint(
				ComplaintType.indexOf(complaintVO.getComplaint_type()), complaintVO.getContent(), complaintVO.getCustomer_id(),
				complaintVO.getEmployee_name(), complaintVO.getId(), Date.valueOf(complaintVO.getDate()),
				Date.valueOf(complaintVO.getProcessing_date()), ComplaintProcessStatus.indexOf(complaintVO.getProcess_status()), complaintVO.getTitle(),
				complaintVO.getResult()));
		}
		return result;
	}

	public Complaint getComplaint(int id) throws NotExistException {
		ComplaintVO complaintVO = complaintMapper.findById_CustomerSupport(id)
			.orElseThrow(() -> new NotExistException("해당하는 민원 정보를 찾을 수 없습니다."));
		return new Complaint(
			ComplaintType.indexOf(complaintVO.getComplaint_type()), complaintVO.getContent(), complaintVO.getCustomer_id(),
			complaintVO.getEmployee_name(), complaintVO.getId(), Date.valueOf(complaintVO.getDate()),
			Date.valueOf(complaintVO.getProcessing_date()), ComplaintProcessStatus.indexOf(complaintVO.getProcess_status()), complaintVO.getTitle(),
			complaintVO.getResult());
	}

	public Customer getCustomer(int customerID) throws NotExistException {
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(customerID)
			.orElseThrow(() -> new NotExistException("해당하는 고객 정보를 찾을 수 없습니다."));
		Customer customer = new Customer();
		customer.setId(customerVO.getId());
		customer.setName(customerVO.getName());
		customer.setPhoneNumber(customerVO.getPhone_number());
		return customer;
	}

	public List<Accident> getAllAccident() {
		List<Accident> result = new ArrayList<>();
		for (AccidentVO accidentVO : accidentMapper.getAll_CustomerSupport()) {
			Optional<CustomerVO> optionalCustomerVO = customerMapper.findById_FinancialAccountant(accidentVO.getCustomer_id());
			if (optionalCustomerVO.isEmpty()) {
				continue;
			}
			CustomerVO customerVO = optionalCustomerVO.get();
			result.add(new Accident(accidentVO.getCustomer_id(), customerVO.getName(),
				customerVO.getPhone_number(), Date.valueOf(accidentVO.getDate()), accidentVO.getId(),
				accidentVO.getLocation(), AccidentProcessStatus.indexOf(accidentVO.getProcess_status()),
				ServiceType.indexOf(accidentVO.getService_type())));
		}
		return result;
	}

	public List<Accident> getAllUnprocessedAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Unprocessed);
	}

	public List<Accident> getAllCompletedAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Completed);
	}

	private List<Accident> getAllByProcessStatus(AccidentProcessStatus processStatus) {
		List<Accident> result = new ArrayList<>();
		for (AccidentVO accidentVO : accidentMapper.findByProcessStatus_CustomerSupport(processStatus.ordinal())) {
			Optional<CustomerVO> optionalCustomerVO = customerMapper.findById_FinancialAccountant(accidentVO.getCustomer_id());
			if (optionalCustomerVO.isEmpty()) {
				continue;
			}
			CustomerVO customerVO = optionalCustomerVO.get();
			result.add(new Accident(accidentVO.getCustomer_id(), customerVO.getName(),
				customerVO.getPhone_number(), Date.valueOf(accidentVO.getDate()), accidentVO.getId(),
				accidentVO.getLocation(), AccidentProcessStatus.indexOf(accidentVO.getProcess_status()),
				ServiceType.indexOf(accidentVO.getService_type())));
		}
		return result;
	}

	public Accident getAccident(int id) throws NotExistException {
		AccidentVO accidentVO = accidentMapper.findByID_CustomerSupport(id)
			.orElseThrow(() -> new NotExistException("해당하는 사고 정보가 존재하지 않습니다."));
		CustomerVO customerVO = customerMapper.findById_FinancialAccountant(accidentVO.getCustomer_id())
			.orElseThrow(() -> new NotExistException("해당하는 고객 정보가 존재하지 않습니다."));
		return new Accident(accidentVO.getCustomer_id(), customerVO.getName(),
			customerVO.getPhone_number(), Date.valueOf(accidentVO.getDate()), accidentVO.getId(),
			accidentVO.getLocation(), AccidentProcessStatus.indexOf(accidentVO.getProcess_status()),
			ServiceType.indexOf(accidentVO.getService_type()));
	}

	public List<PartnerCompany> getAllRoadAssistanceCompany() {
		return getAllByPartnerCompanyType(PartnerCompanyType.RoadsideAssistanceCompany);
	}

	public List<PartnerCompany> getAllDamageAssessmentCompany() {
		return getAllByPartnerCompanyType(PartnerCompanyType.DamageAssessmentCompany);
	}

	private List<PartnerCompany> getAllByPartnerCompanyType(PartnerCompanyType partnerCompanyType) {
		List<PartnerCompany> result = new ArrayList<>();
		for (PartnerCompanyVO partnerCompanyVO :
			partnerCompanyMapper.findByPartnerType_CustomerSupport(partnerCompanyType.ordinal())) {
			result.add(new PartnerCompany(partnerCompanyVO.getEvaluation(), partnerCompanyVO.getHead_name(),
				partnerCompanyVO.getHead_phone_number(), partnerCompanyVO.getId(), partnerCompanyVO.getName(),
				PartnerCompanyType.indexOf(partnerCompanyVO.getPartner_company_type()),
				partnerCompanyVO.getPhone_number(), null));
		}
		return result;
	}

	public PartnerCompany getRoadAssistanceCompany(int id) throws NotExistException {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_CustomerSupport(id)
			.orElseThrow(() -> new NotExistException("해당하는 긴급 출동 업체 정보가 존재하지 않습니다."));
		return new PartnerCompany(partnerCompanyVO.getEvaluation(), partnerCompanyVO.getHead_name(),
			partnerCompanyVO.getHead_phone_number(), partnerCompanyVO.getId(), partnerCompanyVO.getName(),
			PartnerCompanyType.indexOf(partnerCompanyVO.getPartner_company_type()),
			partnerCompanyVO.getPhone_number(), null);
	}

	public PartnerCompany getDamageAssessmentCompany(int id) throws NotExistException {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_CustomerSupport(id)
			.orElseThrow(() -> new NotExistException("해당하는 손해 사정 업체 정보가 존재하지 않습니다."));
		return new PartnerCompany(partnerCompanyVO.getEvaluation(), partnerCompanyVO.getHead_name(),
			partnerCompanyVO.getHead_phone_number(), partnerCompanyVO.getId(), partnerCompanyVO.getName(),
			PartnerCompanyType.indexOf(partnerCompanyVO.getPartner_company_type()),
			partnerCompanyVO.getPhone_number(), null);
	}
}
