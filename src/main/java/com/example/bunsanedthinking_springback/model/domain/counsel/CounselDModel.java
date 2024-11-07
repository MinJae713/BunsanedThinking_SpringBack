package com.example.bunsanedthinking_springback.model.domain.counsel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.repository.CounselMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.CounselVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;

@Service
public class CounselDModel {
	@Autowired
	private CounselMapper counselMapper;
	@Autowired
	private CustomerMapper customerMapper;

	public Counsel getById(int id) {
		CounselVO counselVO = counselMapper.getById(id).orElse(null);
		if (counselVO == null)
			return null;
		CustomerVO customerVO = customerMapper.getById_Customer(counselVO.getCustomer_id()).orElse(null);
		if (customerVO == null)
			return null;
		String name = customerVO.getName();
		String phoneNumber = customerVO.getPhone_number();
		String job = customerVO.getJob();
		int age = customerVO.getAge();
		Gender gender = Gender.fromInt(customerVO.getGender());
		return new Counsel(counselVO, name, phoneNumber, job, age, gender);
	}

	public List<Counsel> getAll() {
		List<Counsel> counsels = new ArrayList<Counsel>();
		for (CounselVO counselVO : counselMapper.getAll_SalesModel())
			counsels.add(getById(counselVO.getId()));
		return counsels;
	}

	public int getMaxId() {
		return counselMapper.getMaxId();
	}

	public void add(CounselVO counselVO) {
		counselMapper.insert(counselVO);
	}

	public void update(CounselVO counselVO) {
		counselMapper.update_SalesModel(counselVO);
	}

	public void delete(int id) {
		counselMapper.deleteById(id);
	}
}
