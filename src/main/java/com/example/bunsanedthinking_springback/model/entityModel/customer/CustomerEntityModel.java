package com.example.bunsanedthinking_springback.model.entityModel.customer;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.complaint.ComplaintEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerEntityModel {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private AccidentHistoryEntityModel accidentHistoryEntityModel;
	@Autowired
	private AccidentEntityModel accidentEntityModel;
	@Autowired
	private CounselEntityModel counselEntityModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private ComplaintEntityModel complaintEntityModel;
	@Autowired
	private DiseaseHistoryEntityModel diseaseHistoryEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@ReadOnly
	public Customer getById(int id) {
		CustomerVO customerVO = customerMapper.getById(id).orElse(null);
		if (customerVO == null)
			return null;
		ArrayList<AccidentHistory> accidentHistories = new ArrayList<AccidentHistory>();
		ArrayList<Accident> accidents = new ArrayList<Accident>();
		ArrayList<Counsel> counsels = new ArrayList<Counsel>();
		ArrayList<SurgeryHistory> surgeryHistories = new ArrayList<SurgeryHistory>();
		ArrayList<Complaint> complaints = new ArrayList<Complaint>();
		ArrayList<DiseaseHistory> diseaseHistories = new ArrayList<DiseaseHistory>();
		ArrayList<Contract> contracts = new ArrayList<Contract>();
		accidentHistoryEntityModel.getAll().stream()
			.filter(e -> e.getCustomerID() == id)
			.forEach(accidentHistories::add);
		accidentEntityModel.getAll().stream()
			.filter(e -> e.getCustomerID() == id)
			.forEach(accidents::add);
		counselEntityModel.getAll().stream()
			.filter(e -> e.getCustomerID() == id)
			.forEach(counsels::add);
		surgeryHistoryEntityModel.getAll().stream()
			.filter(e -> e.getCustomerID() == id)
			.forEach(surgeryHistories::add);
		complaintEntityModel.getAll().stream()
			.filter(e -> e.getCustomerID() == id)
			.forEach(complaints::add);
		diseaseHistoryEntityModel.getAll().stream()
			.filter(e -> e.getCustomer_id() == id)
			.forEach(diseaseHistories::add);
		contractEntityModel.getAll().stream()
			.filter(e -> e.getCustomerID() == id)
			.forEach(contracts::add);
		return customerVO.getEntity(accidentHistories,
			accidents, complaints, contracts,
			counsels, diseaseHistories, surgeryHistories);
	}
	@ReadOnly
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<Customer>();
		customerMapper.getAll()
			.forEach(e -> customers.add(getById(e.getId())));
		return customers;
	}
	@ReadOnly
	public Integer getMaxId() {
		return customerMapper.getMaxId();
	}

	public void add(Customer customer) {
		if (customer == null) return;
		if (customerMapper.getById(customer.getId()).isPresent()) return;
		customerMapper.insert(customer.findVO());

		List<AccidentHistory> accidentHistories = customer.getAccidentHistoryList();
		if (accidentHistories != null) accidentHistories.forEach(e -> accidentHistoryEntityModel.add(e));

		List<Accident> accidents = customer.getAccidentList();
		if (accidents != null) accidents.forEach(e -> accidentEntityModel.add(e));

		List<Counsel> counsels = customer.getCounselList();
		if (counsels != null)
			counsels.forEach(e -> counselEntityModel.add(e));

		List<SurgeryHistory> surgeryHistories = customer.getSurgeryHistoryList();
		if (surgeryHistories != null) surgeryHistories.forEach(e -> surgeryHistoryEntityModel.add(e));

		List<Complaint> complaints = customer.getComplaintList();
		if (complaints != null) complaints.forEach(e -> complaintEntityModel.add(e));

		List<DiseaseHistory> diseaseHistories = customer.getDiseaseHistoryList();
		if (diseaseHistories != null)
			diseaseHistories.forEach(e -> diseaseHistoryEntityModel.add(e));

		List<Contract> contracts = customer.getContractList();
		if (contracts != null)
			contracts.forEach(e -> contractEntityModel.add(e));
	}

	public void update(Customer customer) {
		if (customer == null) return;
		if (customerMapper.getById(customer.getId()).isEmpty()) return;

		List<AccidentHistory> accidentHistories = customer.getAccidentHistoryList();
		if (accidentHistories != null) accidentHistories.forEach(e -> accidentHistoryEntityModel.update(e));

		List<Accident> accidents = customer.getAccidentList();
		if (accidents != null) accidents.forEach(e -> accidentEntityModel.update(e));

		List<Counsel> counsels = customer.getCounselList();
		if (counsels != null)
			counsels.forEach(e -> counselEntityModel.update(e));

		List<SurgeryHistory> surgeryHistories = customer.getSurgeryHistoryList();
		if (surgeryHistories != null) surgeryHistories.forEach(e -> surgeryHistoryEntityModel.update(e));

		List<Complaint> complaints = customer.getComplaintList();
		if (complaints != null) complaints.forEach(e -> complaintEntityModel.update(e));

		List<DiseaseHistory> diseaseHistories = customer.getDiseaseHistoryList();
		if (diseaseHistories != null)
			diseaseHistories.forEach(e -> diseaseHistoryEntityModel.update(e));

		List<Contract> contracts = customer.getContractList();
		if (contracts != null)
			contracts.forEach(e -> contractEntityModel.update(e));

		customerMapper.update(customer.findVO());
	}

	public void delete(int id) {
		if (customerMapper.getById(id).isEmpty()) return;
		Customer customer = getById(id);

		List<AccidentHistory> accidentHistories = customer.getAccidentHistoryList();
		if (accidentHistories != null) accidentHistories.forEach(e -> accidentHistoryEntityModel.delete(e.getId()));

		List<Accident> accidents = customer.getAccidentList();
		if (accidents != null) accidents.forEach(e -> accidentEntityModel.delete(e.getId()));

		List<Counsel> counsels = customer.getCounselList();
		if (counsels != null)
			counsels.forEach(e -> counselEntityModel.delete(e.getId()));

		List<SurgeryHistory> surgeryHistories = customer.getSurgeryHistoryList();
		if (surgeryHistories != null) surgeryHistories.forEach(e -> surgeryHistoryEntityModel.delete(e.getId()));

		List<Complaint> complaints = customer.getComplaintList();
		if (complaints != null) complaints.forEach(e -> complaintEntityModel.delete(e.getId()));

		List<DiseaseHistory> diseaseHistories = customer.getDiseaseHistoryList();
		if (diseaseHistories != null)
			diseaseHistories.forEach(e -> diseaseHistoryEntityModel.delete(e.getId()));

		List<Contract> contracts = customer.getContractList();
		if (contracts != null)
			contracts.forEach(e -> contractEntityModel.delete(e.getId()));

		customerMapper.deleteById(id);
	}
}
