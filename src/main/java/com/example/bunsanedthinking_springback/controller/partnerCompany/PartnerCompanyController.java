package com.example.bunsanedthinking_springback.controller.partnerCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.partnerCompany.UpdateReportDTO;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.partnerCompany.PartnerCompanyService;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;

@RestController
@RequestMapping("/partnerCompany")
public class PartnerCompanyController {

	@Autowired
	private PartnerCompanyService partnerCompanySModel;

	@GetMapping("/getPartnerCompany")
	public PartnerCompanyVO getPartnerCompany(@RequestParam int id) throws NotExistException {
		return partnerCompanySModel.getPartnerCompany(id);
	}

	// @GetMapping("/getAllReportByDamageAssessmentCompanyID")
	// public ArrayList<ReportVO> getAllReportByDamageAssessmentCompanyID(@RequestParam int id) {
	// 	return partnerCompanySModel.getAllReportByDamageAssessmentCompanyID(id);
	// }

	// @GetMapping("/getReport")
	// public ReportVO getReport(@RequestParam int id) throws NotExistException {
	// 	return partnerCompanySModel.getReport(id);
	// }

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
