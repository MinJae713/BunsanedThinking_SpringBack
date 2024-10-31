package com.example.bunsanedthinking_springback.entity.report;

import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public interface ReportList {

	/**
	 * 
	 * @param report
	 */
	public void add(Report report);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) throws NotExistException;

	/**
	 * 
	 * @param id
	 */
	public Report get(int id) throws NotExistException;

	/**
	 * 
	 * @param id
	 */
	public void update(Report report) throws NotExistException;

	public ArrayList<Report> getAll();
	
	public ArrayList<Report> getAllReportByDamageAssessmentCompanyID(int id);

	public ArrayList<Report> getAllUnprocessedReport();

	public ArrayList<Report> getAllCompletedReport();

}