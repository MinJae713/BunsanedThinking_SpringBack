package com.example.bunsanedthinking_springback.model.domain.surgeryHistory;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurgeryHistoryDModel {
	@Autowired
	private SurgeryHistoryMapper surgeryHistoryMapper;

	public SurgeryHistory getById(int id) {
		return surgeryHistoryMapper.getById(id)
			.map(SurgeryHistory::new)
			.orElse(null);
	}

	public List<SurgeryHistory> getAll() {
		List<SurgeryHistory> surgeryHistories = new ArrayList<SurgeryHistory>();
		surgeryHistoryMapper.getAll()
			.forEach(e -> surgeryHistories.add(getById(e.getId())));
		return surgeryHistories;
	}

	public Integer getMaxId() {
		return surgeryHistoryMapper.getMaxId_SalesModel();
	}

	public void add(SurgeryHistory surgeryHistory) {
		surgeryHistoryMapper.insert_SalesModel(surgeryHistory.findVO());
	}

	public void update(SurgeryHistory surgeryHistory) {
		surgeryHistoryMapper.update(surgeryHistory.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		surgeryHistoryMapper.deleteById(id);
	}
}
