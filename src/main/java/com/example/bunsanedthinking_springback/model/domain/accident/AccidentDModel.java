package com.example.bunsanedthinking_springback.model.domain.accident;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.repository.AccidentMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;

@Service
public class AccidentDModel {
	@Autowired
	private AccidentMapper accidentMapper;
	@Autowired
	private CustomerMapper customerMapper;

	public Accident getById(int id) {
		AccidentVO accidentVO = accidentMapper.getById_Compensation(id).orElse(null);
		if (accidentVO == null)
			return null;
		int customerId = accidentVO.getCustomer_id();
		CustomerVO customerVO = customerMapper.getById_Customer(customerId).orElse(null);
		if (customerVO == null)
			return null;
		String name = customerVO.getName();
		String phoneNumber = customerVO.getPhone_number();
		return new Accident(accidentVO, name, phoneNumber);
	}

	public List<Accident> getAll() {
		List<Accident> accidents = new ArrayList<Accident>();
		for (AccidentVO accidentVO : accidentMapper.getAll_CustomerSupport())
			accidents.add(getById(accidentVO.getId()));
		return accidents;
	}

	public int getMaxId() {
		return accidentMapper.getMaxId();
	}

	public void add(AccidentVO accidentVO) {
		accidentMapper.insert(accidentVO);
	}

	public void update(AccidentVO accidentVO) {
		accidentMapper.update_CustomerSupport(accidentVO);
	}

	public void delete(int id) {
		accidentMapper.delete(id);
	}
}
