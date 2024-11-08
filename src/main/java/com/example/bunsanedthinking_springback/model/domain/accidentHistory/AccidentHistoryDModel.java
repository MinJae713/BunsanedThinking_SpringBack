package com.example.bunsanedthinking_springback.model.domain.accidentHistory;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.repository.AccidentHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
		accidentHistoryMapper.getAll().forEach(e -> accidentHistories.add(getById(e.getId())));
		return accidentHistories;
	}

	public Integer getMaxId() {
		return accidentHistoryMapper.getMaxId_SalesModel();
	}

	public void add(AccidentHistory accidentHistory) {
		accidentHistoryMapper.insert(accidentHistory.findVO());
	}

	public void update(AccidentHistory accidentHistory) {
		accidentHistoryMapper.update(accidentHistory.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		accidentHistoryMapper.deleteById(id);
	}
}
