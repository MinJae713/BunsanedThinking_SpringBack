package com.example.bunsanedthinking_springback.model.service.employee.customerSupport;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleComplaint.HandleComplaintResponse;
import com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleReport.HandleReportResponse;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentProcessStatus;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.complaint.ComplaintEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.partnerCompany.PartnerCompanyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportEntityModel;

@Service
public class CustomerSupportService {
	@Autowired
	private ComplaintEntityModel complaintEntityModel;
	@Autowired
	private PartnerCompanyEntityModel partnerCompanyEntityModel;
	@Autowired
	private AccidentEntityModel accidentEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ReportEntityModel reportEntityModel;
	@Autowired
	private EmployeeEntityModel employeeEntityModel;

	public void handleComplaint(String employeeName, int complaintId, String result) throws
		NotExistException,
		AlreadyProcessedException {
		Complaint complaint = complaintEntityModel.getById(complaintId);
		if (complaint == null)
			throw new NotExistException("해당하는 민원 정보를 찾을 수 없습니다.");
		if (complaint.getProcessStatus() == ComplaintProcessStatus.Completed)
			throw new AlreadyProcessedException("이미 민원 처리가 완료되었습니다.");
		boolean isExistEmployee = employeeEntityModel.getAll()
			.stream()
			.anyMatch(employee -> employee.getName().equals(employeeName));
		if (!isExistEmployee)
			throw new NotExistException("해당하는 직원 정보를 찾을 수 없습니다.");
		complaint.setEmployeeName(employeeName);
		complaint.setResult(result);
		complaint.setProcessingDate(Date.valueOf(LocalDate.now()));
		complaint.setProcessStatus(ComplaintProcessStatus.Completed);
		complaintEntityModel.update(complaint);
	}

	public void handleAccident(int accidentId, int damageAssessmentCompanyId,
		int roadsideAssistanceCompanyId) throws AlreadyProcessedException {
		Accident accident = accidentEntityModel.getById(accidentId);
		if (accident == null) {
			return;
		}
		switch (accident.getProcessStatus()) {
			case Completed -> throw new AlreadyProcessedException("이미 신고 처리가 완료되었습니다.");
			case Processing -> throw new AlreadyProcessedException("신고 처리 중입니다.");
		}
		accident.setProcessStatus(AccidentProcessStatus.Processing);
		accidentEntityModel.update(accident);

		Report report = new Report(accident, null, accidentId, roadsideAssistanceCompanyId, damageAssessmentCompanyId,
			ReportProcessStatus.Unprocessed);
		reportEntityModel.add(report);
	}

	public List<HandleComplaintResponse> getAllComplaint() {
		List<Complaint> complaintList = complaintEntityModel.getAll();
		return complaintList.stream()
			.map(complaint -> HandleComplaintResponse.of(complaint,
				customerEntityModel.getById(complaint.getCustomerID())))
			.toList();
	}

	public List<HandleComplaintResponse> getAllUnprocessedComplaint() {
		List<Complaint> complaintList = getAllByProcessStatus(ComplaintProcessStatus.Unprocessed);
		return complaintList.stream()
			.map(complaint -> HandleComplaintResponse.of(complaint,
				customerEntityModel.getById(complaint.getCustomerID())))
			.toList();
	}

	public List<HandleComplaintResponse> getAllProcessedComplaint() {
		List<Complaint> complaintList = getAllByProcessStatus(ComplaintProcessStatus.Completed);
		return complaintList.stream()
			.map(complaint -> HandleComplaintResponse.of(complaint,
				customerEntityModel.getById(complaint.getCustomerID())))
			.toList();
	}

	private List<Complaint> getAllByProcessStatus(ComplaintProcessStatus processStatus) {
		List<Complaint> complaintList = complaintEntityModel.getAll();
		return complaintList.stream()
			.filter(complaint -> complaint.getProcessStatus() == processStatus)
			.collect(Collectors.toList());
	}

	public HandleComplaintResponse getComplaint(int id) throws NotExistException {
		Complaint complaint = complaintEntityModel.getById(id);
		if (complaint == null)
			throw new NotExistException("해당하는 민원 정보가 존재하지 않습니다.");
		Customer customer = customerEntityModel.getById(complaint.getCustomerID());
		if (customer == null)
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		return HandleComplaintResponse.of(complaint, customer);
	}

	public HandleComplaintResponse getComplaintDetail(int id) throws NotExistException {
		Complaint complaint = complaintEntityModel.getById(id);
		if (complaint == null)
			throw new NotExistException("해당하는 민원 정보가 존재하지 않습니다.");
		Customer customer = customerEntityModel.getById(complaint.getCustomerID());
		if (customer == null)
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		return HandleComplaintResponse.ofWithDetail(complaint, customer);
	}

	public Customer getCustomer(int customerID) throws NotExistException {
		Customer customer = customerEntityModel.getById(customerID);
		if (customer == null)
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		return customer;
	}

	public List<HandleReportResponse> getAllAccident() {
		return accidentEntityModel.getAll().stream()
			.map(HandleReportResponse::from)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}

	public List<HandleReportResponse> getAllUnprocessedAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Unprocessed);
	}

	public List<HandleReportResponse> getAllCompletedAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Completed);
	}

	public List<HandleReportResponse> getAllProcessingAccident() {
		return getAllByProcessStatus(AccidentProcessStatus.Processing);
	}

	private List<HandleReportResponse> getAllByProcessStatus(AccidentProcessStatus processStatus) {
		List<Accident> accidentList = accidentEntityModel.getAll();
		return accidentList.stream()
			.filter(accident -> accident.getProcessStatus() == processStatus)
			.map(HandleReportResponse::from)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}

	public HandleReportResponse getAccident(int id) throws NotExistException {
		Accident accident = accidentEntityModel.getById(id);
		if (accident == null)
			throw new NotExistException("해당하는 사고 정보가 존재하지 않습니다.");
		return HandleReportResponse.from(accident);
	}

	public List<PartnerCompany> getAllRoadAssistanceCompany() {
		return partnerCompanyEntityModel.getAll_RoadsideCompany();
	}

	public List<PartnerCompany> getAllDamageAssessmentCompany() {
		return partnerCompanyEntityModel.getAll_DamageAssesmentCompany();
	}

	public PartnerCompany getRoadAssistanceCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyEntityModel.getById(id);
		if (partnerCompany == null)
			throw new NotExistException("해당하는 긴급 출동 업체 정보가 존재하지 않습니다.");
		return partnerCompany;
	}

	public PartnerCompany getDamageAssessmentCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyEntityModel.getById(id);
		if (partnerCompany == null)
			throw new NotExistException("해당하는 손해 사정 업체 정보가 존재하지 않습니다.");
		return partnerCompany;
	}

	public Employee getEmployee(int employeeId) throws NotExistException {
		Employee employee = employeeEntityModel.getById(employeeId);
		if (employee == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		return employee;
	}
}
