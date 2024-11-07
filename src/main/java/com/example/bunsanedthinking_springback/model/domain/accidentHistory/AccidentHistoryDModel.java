package com.example.bunsanedthinking_springback.model.domain.accidentHistory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.repository.AccidentHistoryMapper;
import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;

@Service
public class AccidentHistoryDModel {
	@Autowired
	private AccidentHistoryMapper accidentHistoryMapper;

	public AccidentHistory getById(int id) {
		return accidentHistoryMapper.getById(id)
			.map(AccidentHistory::new)
			.orElse(null);
	}

	public List<AccidentHistory> getAll() {
		List<AccidentHistory> accidentHistories = new ArrayList<AccidentHistory>();
		accidentHistoryMapper.getAll()
			.forEach(e -> accidentHistories.add(new AccidentHistory(e)));
		return accidentHistories;
	}

	public int getMaxId() {
		return accidentHistoryMapper.getMaxId_SalesModel();
	}

	public void add(AccidentHistoryVO accidentHistoryVO) {
		accidentHistoryMapper.insert(accidentHistoryVO);
	}

	public void update(AccidentHistoryVO accidentHistoryVO) {
		accidentHistoryMapper.update(accidentHistoryVO);
	}

	public void delete(int id) {
		accidentHistoryMapper.deleteById(id);
	}
}
