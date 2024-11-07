package com.example.bunsanedthinking_springback.model.domain.report;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.model.domain.accident.AccidentDModel;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportDModel {
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private AccidentDModel accidentDModel;
    public Report getById(int id) {
        ReportVO reportVO = reportMapper.getById_Compensation(id).orElse(null);
        Accident accident = accidentDModel.getById(id);
        return reportVO.getEntity(accident);
    }
    public ArrayList<Report> getAllByRoadSideAssistanceCId(int id) {
        ArrayList<Report> reports = new ArrayList<>();
        List<ReportVO> reportVOS = reportMapper.getAllByRoadSideAssistanceCId(id);
        for (ReportVO reportVO : reportVOS) {
            Accident accident = accidentDModel.getById(reportVO.getAccident_id());
            reports.add(reportVO.getEntity(accident));
        }
        return reports;
    }
    public ArrayList<Report> getAllByDamageAssessmentCId(int id) {
        ArrayList<Report> reports = new ArrayList<>();
        List<ReportVO> reportVOS = reportMapper.findAllByDamageAssessmentCompanyID_PartnerCompany(id);
        for (ReportVO reportVO : reportVOS) {
            Accident accident = accidentDModel.getById(reportVO.getAccident_id());
            reports.add(reportVO.getEntity(accident));
        }
        return reports;
    }
    public List<Report> getAll() {
        List<Report> reports = new ArrayList<Report>();
        for (ReportVO reportVO : reportMapper.getAll_Compensation()) {
            Accident accident = accidentDModel.getById(reportVO.getAccident_id());
            reports.add(reportVO.getEntity(accident));
        }
        return reports;
    }
    public int getMaxId() {
        return reportMapper.getMaxId();
    }
    public void add(ReportVO reportVO) {
        reportMapper.insert_CustomerSupport(reportVO);
    }
    public void update(ReportVO reportVO) {
        reportMapper.update(reportVO);
    }
    public void delete(int id) {
        reportMapper.deleteById(id);
    }
}
