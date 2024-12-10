package com.example.bunsanedthinking_springback.model.entityModel.counsel;

import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselProcessStatus;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.CounselMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.CounselVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounselEntityModel {
	@Autowired
	private CounselMapper counselMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@ReadOnly
	public Counsel getById(int id) {
		CounselVO counselVO = counselMapper.getById(id).orElse(null);
		if (counselVO == null)
			return null;
		CustomerVO customerVO = customerMapper.getById(counselVO.getCustomer_id()).orElse(null);
		if (customerVO == null)
			return null;
		String name = customerVO.getName();
		String phoneNumber = customerVO.getPhone_number();
		String job = customerVO.getJob();
		int age = customerVO.getAge();
		Gender gender = Gender.fromInt(customerVO.getGender());
		return new Counsel(counselVO, name, phoneNumber, job, age, gender);
	}
	@ReadOnly
	public List<Counsel> getAll() {
		List<Counsel> counsels = new ArrayList<Counsel>();
		counselMapper.getAll().forEach(e -> counsels.add(getById(e.getId())));
		return counsels;
	}
	@ReadOnly
	public Integer getMaxId() {
		return counselMapper.getMaxId();
	}

	public void add(Counsel counsel) {
		counselMapper.insert(counsel.findVO());
	}

	public void update(Counsel counsel) {
		counselMapper.update(counsel.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null)
			return;
		counselMapper.deleteById(id);
	}
	@ReadOnly
	public List<Counsel> getAllCompleted() {
		List<Counsel> counsels = new ArrayList<Counsel>();
		counselMapper.getAll().stream().filter(e-> e.getProcess_status() == CounselProcessStatus.Completed.getValue()).forEach(e -> counsels.add(getById(e.getId())));
		return counsels;
	}
	@ReadOnly
	public List<Counsel> getAllUnprocessed() {
		List<Counsel> counsels = new ArrayList<Counsel>();
		counselMapper.getAll().stream().filter(e-> e.getProcess_status() == CounselProcessStatus.Unprocessed.getValue()).forEach(e -> counsels.add(getById(e.getId())));
		return counsels;
	}
}
