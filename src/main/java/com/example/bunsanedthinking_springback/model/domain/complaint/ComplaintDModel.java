package com.example.bunsanedthinking_springback.model.domain.complaint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.repository.ComplaintMapper;
import com.example.bunsanedthinking_springback.vo.ComplaintVO;

@Service
public class ComplaintDModel {
	@Autowired
	private ComplaintMapper complaintMapper;

	public Complaint getById(int id) {
		return complaintMapper.getComplaintById_Customer(id)
			.map(ComplaintVO::getEntity)
			.orElse(null);
	}

	public List<Complaint> getAll() {
		List<Complaint> complaints = new ArrayList<Complaint>();
		complaintMapper.getAll_CustomerSupport()
			.forEach(e -> complaints.add(e.getEntity()));
		return complaints;
	}

	public Integer getMaxId() {
		return complaintMapper.getMaxId();
	}

	public void add(ComplaintVO complaintVO) {
		complaintMapper.insert(complaintVO);
	}

	public void update(ComplaintVO complaintVO) {
		complaintMapper.update_CustomerSupport(complaintVO);
	}

	public void delete(int id) {
		complaintMapper.deleteById(id);
	}
}
