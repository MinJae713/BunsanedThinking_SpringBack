package com.example.bunsanedthinking_springback.model.entityModel.complaint;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.repository.ComplaintMapper;
import com.example.bunsanedthinking_springback.vo.ComplaintVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintEntityModel {
	@Autowired
	private ComplaintMapper complaintMapper;

	public Complaint getById(int id) {
		return complaintMapper.getById(id)
			.map(ComplaintVO::getEntity)
			.orElse(null);
	}

	public List<Complaint> getAll() {
		List<Complaint> complaints = new ArrayList<Complaint>();
		complaintMapper.getAll().forEach(e -> complaints.add(getById(e.getId())));
		return complaints;
	}

	public Integer getMaxId() {
		return complaintMapper.getMaxId();
	}

	public void add(Complaint complaint) {
		complaintMapper.insert(complaint.findVO());
	}

	public void update(Complaint complaint) {
		complaintMapper.update(complaint.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		complaintMapper.deleteById(id);
	}
}
