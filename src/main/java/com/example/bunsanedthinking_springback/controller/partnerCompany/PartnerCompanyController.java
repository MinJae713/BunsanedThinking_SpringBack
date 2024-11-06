package com.example.bunsanedthinking_springback.controller.partnerCompany;

import com.example.bunsanedthinking_springback.dto.partnerCompany.UpdateReportDTO;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.partnerCompany.PartnerCompanySModel;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import com.example.bunsanedthinking_springback.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/partnerCompany")
public class PartnerCompanyController {

	@Autowired
	private PartnerCompanySModel partnerCompanySModel;

	@GetMapping("/getPartnerCompany")
	public PartnerCompanyVO getPartnerCompany(@RequestParam int id) throws NotExistException {
		return partnerCompanySModel.getPartnerCompany(id);
	}

	@GetMapping("/getAllReportByDamageAssessmentCompanyID")
	public ArrayList<ReportVO> getAllReportByDamageAssessmentCompanyID(@RequestParam int id) {
		return partnerCompanySModel.getAllReportByDamageAssessmentCompanyID(id);
	}

	@GetMapping("/getReport")
	public ReportVO getReport(@RequestParam int id) throws NotExistException {
		return partnerCompanySModel.getReport(id);
	}

	@PatchMapping("/updateReport")
	public void updateReport(@RequestBody UpdateReportDTO updateReportDTO) throws NotExistException {
		partnerCompanySModel.update(updateReportDTO.getReport());
		// 이 부분 @RequestBody 물어보기
	}

	@PatchMapping("/setDamageAssessmentMoney")
	public void setDamageAssessmentMoney(@RequestParam("reportId") int reportId,
			@RequestParam("damageAssessmentMoney") int damageAssessmentMoney) throws NotExistException {
		partnerCompanySModel.setDamageAssessmentMoney(reportId, damageAssessmentMoney);
	}
}
