package com.example.bunsanedthinking_springback.model.entityModel.accident;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.repository.AccidentMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentEntityModel {
	@Autowired
	private AccidentMapper accidentMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@ReadOnly
	public Accident getById(int id) {
		AccidentVO accidentVO = accidentMapper.getById(id).orElse(null);
		if (accidentVO == null)
			return null;
		int customerId = accidentVO.getCustomer_id();
		CustomerVO customerVO = customerMapper.getById(customerId).orElse(null);
		if (customerVO == null)
			return null;
		String name = customerVO.getName();
		String phoneNumber = customerVO.getPhone_number();
		return accidentVO.getEntity(name, phoneNumber);
	}
	@ReadOnly
	public List<Accident> getAll() {
		List<Accident> accidents = new ArrayList<Accident>();
		accidentMapper.getAll().forEach(e -> accidents.add(getById(e.getId())));
		return accidents;
	}
	@ReadOnly
	public Integer getMaxId() {
		return accidentMapper.getMaxId();
	}

	public void add(Accident accident) {
		accidentMapper.insert(accident.findVO());
	}

	public void update(Accident accident) {
		accidentMapper.update(accident.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null)
			return;
		accidentMapper.deleteById(id);
	}
}
