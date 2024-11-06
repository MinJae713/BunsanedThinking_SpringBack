package com.example.bunsanedthinking_springback.model.domain.surgeryHistory;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurgeryHistoryEModel {
    @Autowired
    private SurgeryHistoryMapper surgeryHistoryMapper;
    public SurgeryHistory getById(int id) {
        SurgeryHistoryVO surgeryHistoryVO = surgeryHistoryMapper.getById(id).orElse(null);
        return new SurgeryHistory(surgeryHistoryVO);
    }
    public List<SurgeryHistory> getAll() {
        List<SurgeryHistory> surgeryHistories = new ArrayList<SurgeryHistory>();
        surgeryHistoryMapper.getAll().stream().forEach(e -> surgeryHistories.add(new SurgeryHistory(e)));
        return surgeryHistories;
    }
    public int getMaxId() {
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
