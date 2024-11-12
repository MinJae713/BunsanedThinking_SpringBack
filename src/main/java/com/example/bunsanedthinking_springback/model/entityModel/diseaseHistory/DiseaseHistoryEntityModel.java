package com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.repository.DiseaseHistoryMapper;
import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;

@Service
public class DiseaseHistoryEntityModel {
	@Autowired
	private DiseaseHistoryMapper diseaseHistoryMapper;

	public DiseaseHistory getById(int id) {
		DiseaseHistoryVO diseaseHistoryVO = diseaseHistoryMapper.getById(id).orElse(null);
		if (diseaseHistoryVO == null)
			return null;
		return new DiseaseHistory(diseaseHistoryVO);
	}

	public List<DiseaseHistory> getAll() {
		List<DiseaseHistory> diseaseHistories = new ArrayList<DiseaseHistory>();
		diseaseHistoryMapper.getAll()
			.forEach(e -> diseaseHistories.add(new DiseaseHistory(e)));
		return diseaseHistories;
	}

	public Integer getMaxId() {
		return diseaseHistoryMapper.getMaxId();
	}

	public void add(DiseaseHistory diseaseHistory) {
		diseaseHistoryMapper.insert(diseaseHistory.findVO());
	}

	public void update(DiseaseHistory diseaseHistory) {
		diseaseHistoryMapper.update(diseaseHistory.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null)
			return;
		diseaseHistoryMapper.deleteById(id);
	}
}
