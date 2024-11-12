package com.example.bunsanedthinking_springback.model.entityModel.accident;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.repository.AccidentMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AccidentEntityModel {
	@Autowired
	private AccidentMapper accidentMapper;
	@Autowired
	private CustomerMapper customerMapper;

	@Value("${serials.accident}")
	public static int ACCIDENT_SERIAL_NUMBER;

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

	public List<Accident> getAll() {
		List<Accident> accidents = new ArrayList<Accident>();
		accidentMapper.getAll().forEach(e -> accidents.add(getById(e.getId())));
		return accidents;
	}

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
