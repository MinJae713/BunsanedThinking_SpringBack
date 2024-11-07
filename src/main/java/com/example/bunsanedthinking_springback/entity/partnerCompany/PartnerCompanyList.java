package com.example.bunsanedthinking_springback.entity.partnerCompany;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public interface PartnerCompanyList {

	/**
	 *
	 * @param partnerCompany
	 */
	public void add(PartnerCompany partnerCompany);

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException;

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public PartnerCompany get(int id) throws NotExistException;

	/**
	 *
	 * @param partnerCompany
	 * @throws NotExistException
	 */
	public void update(PartnerCompany partnerCompany) throws NotExistException;

	public ArrayList<PartnerCompany> getAllRoadAssistanceCompany();

	public ArrayList<PartnerCompany> getAllDamageAssessmentCompany();

	public ArrayList<PartnerCompany> getAll();

	public PartnerCompany getDamageAssessmentCompany(int id) throws NotExistException;

	public PartnerCompany getRoadAssistanceCompany(int id) throws NotExistException;

}
