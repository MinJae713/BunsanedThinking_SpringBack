package com.example.bunsanedthinking_springback.model.domain.surgeryHistory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

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
			.forEach(e -> surgeryHistories.add(new SurgeryHistory(e)));
		return surgeryHistories;
	}

	public Integer getMaxId() {
		return surgeryHistoryMapper.getMaxId_SalesModel();
	}

	public void add(SurgeryHistoryVO surgeryHistoryVO) {
		surgeryHistoryMapper.insert_SalesModel(surgeryHistoryVO);
	}

	public void update(SurgeryHistoryVO surgeryHistoryVO) {
		surgeryHistoryMapper.update(surgeryHistoryVO);
	}

	public void delete(int id) {
		surgeryHistoryMapper.deleteById(id);
	}
}
