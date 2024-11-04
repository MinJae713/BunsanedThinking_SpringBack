package com.example.bunsanedthinking_springback.entity.report;

import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public class ReportListImpl implements ReportList {

	private static int index = 0;
	private ArrayList<Report> reportList;

	public ReportListImpl(){
		this.reportList = new ArrayList<>();
	}

	/**
	 * 
	 * @param report
	 */
	public void add(Report report){
		String compose = "" + Report.REPORT_SERIAL_NUMBER + (++index);
		report.setId(Integer.parseInt(compose));
		this.reportList.add(report);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) throws NotExistException {
		for (Report report : reportList) {
			if (report.getId() == id) {
				reportList.remove(report);
				break;
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public Report get(int id) throws NotExistException{
		for (Report report : reportList) {
			if (report.getId() == id) {
				return report;
			}
		}
		throw new NotExistException();
	}

	/**
	 *
	 */
	public void update(Report report) throws NotExistException{
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).getId() == report.getId()) {
				reportList.set(i, report);
				return;
			}
		}
	}

	@Override
	public ArrayList<Report> getAll() {
		return reportList;
	}

	@Override
	public ArrayList<Report> getAllUnprocessedReport() {
		ArrayList<Report> reports = new ArrayList<>();
		for (Report report : reportList) {
			if (report.getProcessStatus() == ReportProcessStatus.Unprocessed) {
				reports.add(report);
			}
		}
		return reports;
	}
	
	@Override
	public ArrayList<Report> getAllCompletedReport() {
		ArrayList<Report> reports = new ArrayList<>();
		for (Report report : reportList) {
			if (report.getProcessStatus() == ReportProcessStatus.Completed) {
				reports.add(report);
			}
		}
		return reports;
	}

	@Override
	public ArrayList<Report> getAllReportByDamageAssessmentCompanyID(int id) {
		ArrayList<Report> reports = new ArrayList<>();
		for (Report report : reportList) {
			if (report.getDamageAssessmentCompanyID() ==id) {
				reports.add(report);
			}
		}
		return reports;
	}

}