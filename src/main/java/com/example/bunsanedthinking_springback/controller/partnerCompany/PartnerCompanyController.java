package com.example.bunsanedthinking_springback.controller.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.partnerCompany.PartnerCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partnerCompany")
public class PartnerCompanyController {

	@Autowired
	private PartnerCompanyService partnerCompanySModel;

	@GetMapping("/getPartnerCompany")
	public PartnerCompany getPartnerCompany(@RequestParam int id) throws NotExistException {
		return partnerCompanySModel.getPartnerCompany(id);
	}

	 @GetMapping("/getAllReportByDamageAssessmentCompanyID")
	 public List<Report> getAllReportByDamageAssessmentCompanyID(@RequestParam int id) throws NotExistException {
	 	return partnerCompanySModel.getAllReportByDamageAssessmentCompanyID(id);
	 }

	 @GetMapping("/getReport")
	 public Report getReport(@RequestParam int id) throws NotExistException {
	 	return partnerCompanySModel.getReport(id);
	 }

//	@PatchMapping("/updateReport")
//	public void updateReport(@RequestBody UpdateReportDTO updateReportDTO) throws NotExistException {
//		partnerCompanySModel.update(updateReportDTO.getReport());
//		// 이 부분 @RequestBody 물어보기
//	}

	@PatchMapping("/setDamageAssessmentMoney")
	public void setDamageAssessmentMoney(@RequestParam("accidentId") int accidentId,
										 @RequestParam("damageAssessmentMoney") int damageAssessmentMoney) throws NotExistException {
		partnerCompanySModel.setDamageAssessmentMoney(accidentId, damageAssessmentMoney);
	}

}
