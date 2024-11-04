package com.example.bunsanedthinking_springback.entity.contract;

import com.example.bunsanedthinking_springback.exception.NotExistContractException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public interface ContractList {

	/**
	 * 
	 * @param contract
	 */
	public void add(Contract contract);

	/**
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 
	 * @param id
	 * @throws NotExistContractException
	 */
	public Contract get(int id) throws NotExistContractException;

	/**
	 * 
	 * @param contract
	 * @throws NotExistContractException 
	 */
	public void update(Contract contract) throws NotExistContractException;

	public ArrayList<Contract> getAllByCustomer(int id) throws NotExistContractException;
	public Contract getContractByOneAutomobileId(int customerID) throws NotExistContractException;
	
	public ArrayList<Contract> getAllRequestingInsurance();
	public ArrayList<Contract> getAllNotRequestingInsurance();
	
	//고객
	public ArrayList<Contract> getAllAutomobileInsuranceContract();
	public ArrayList<Contract> getAllInjuryInsuranceContract();
	public ArrayList<Contract> getAllDiseaseInsuranceContract();
	public ArrayList<Contract> getContractByProductId(int id);
	public ArrayList<Contract> getAllDefaultContract();

	
	public ArrayList<Contract> getAllRequestingLoan();
	public ArrayList<Contract> getAllNotRequestingLoan();

	public Contract getRequestingLoan(int id) throws NotExistContractException;

	public ArrayList<Contract> getAllLoanContract();

	public Contract getLoanContract(int id) throws NotExistContractException;

	public Contract getNotRequestingLoan(int id) throws NotExistContractException;

	public Contract getNotRequestingLoanByCustomer(int id) throws NotExistContractException;

	public ArrayList<Contract> getAllApprovedByCustomer(int id) throws NotExistContractException;

}