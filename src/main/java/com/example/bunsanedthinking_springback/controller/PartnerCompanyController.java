package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.mo.UpdateReportDTO;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.partnerCompany.PartnerCompanyModel;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import com.example.bunsanedthinking_springback.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/partnerCompany")
public class PartnerCompanyController {

	@Autowired
	private PartnerCompanyModel partnerCompanyModel;

	@GetMapping("/getPartnerCompany")
	public PartnerCompanyVO getPartnerCompany(@RequestParam int id) throws NotExistException {
		return partnerCompanyModel.getPartnerCompany(id);
	}

	@GetMapping("/getAllReportByDamageAssessmentCompanyID")
	public ArrayList<ReportVO> getAllReportByDamageAssessmentCompanyID(@RequestParam int id) {
		return partnerCompanyModel.getAllReportByDamageAssessmentCompanyID(id);
	}

	@GetMapping("/getReport")
	public ReportVO getReport(@RequestParam int id) throws NotExistException {
		return partnerCompanyModel.getReport(id);
	}

	@PatchMapping("/updateReport")
	public void updateReport(@RequestBody UpdateReportDTO updateReportDTO) throws NotExistException {
		partnerCompanyModel.update(updateReportDTO.getReport());
		// 이 부분 @RequestBody 물어보기
	}
}
