package com.example.bunsanedthinking_springback.entity.contract;

import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:40
 */
public class ContractListImpl implements ContractList {

	static private int index = 0;

	private ArrayList<Contract> contractList;

	public ContractListImpl() {
		this.contractList = new ArrayList<>();
	}

	/**
	 * 
	 * @param contract
	 */
	public void add(Contract contract) {
		String compose = "" + Contract.CONTRACT_SERIAL_NUMBER + ContractListImpl.index;
		contract.setId(Integer.parseInt(compose));
		ContractListImpl.index++;
		this.contractList.add(contract);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) {
		for (int i = 0; i < contractList.size(); i++) {
			if (contractList.get(i).getId() == id) {
				contractList.remove(i);
				return;
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistContractException
	 */
	public Contract get(int id) throws NotExistContractException {
		for (int i = 0; i < contractList.size(); i++) {
			if (contractList.get(i).getId() == id) {
				return contractList.get(i).clone();
			}
		}
		throw new NotExistContractException();
	}

	/**
	 *
	 * @throws NotExistContractException
	 */
	public void update(Contract contract) throws NotExistContractException {
		for (int i = 0; i < contractList.size(); i++) {
			if (contractList.get(i).getId() == contract.getId()) {
				contractList.set(i, contract);
				return;
			}
		}
		throw new NotExistContractException();
	}

////怨좉컼////
	public ArrayList<Contract> getContractByProductId(int id) {
		ArrayList<Contract> contractList = new ArrayList<>();
		for (Contract contract : this.contractList) {
			if (contract.getProduct().getId() == id) {
				contractList.add(contract);
			}
		}
		return contractList;
	}

	public ArrayList<Contract> getAllAutomobileInsuranceContract() {
		ArrayList<Contract> automobilecontractList = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Automobile) {
				automobilecontractList.add(contract);
			}
		}
		return automobilecontractList;
	}

	public ArrayList<Contract> getAllInjuryInsuranceContract() {
		ArrayList<Contract> injurycontractList = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Injury) {
				injurycontractList.add(contract);
			}
		}
		return injurycontractList;
	}

	public ArrayList<Contract> getAllDiseaseInsuranceContract() {
		ArrayList<Contract> injurycontractList = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Disease) {
				injurycontractList.add(contract);
			}
		}
		return injurycontractList;
	}
	//// 吏곸썝///
	//// 怨좉컼 �븘�씠�뵒濡� 李얘린////

	public ArrayList<Contract> getAllByCustomer(int id) throws NotExistContractException {
		ArrayList<Contract> contractList = new ArrayList<>();
		for (Contract contract : this.contractList) {
			if (contract.getCustomerID() == id) {
				contractList.add(contract);
			}
		}
		if (contractList.isEmpty()) {
			throw new NotExistContractException();
		}
		return contractList;
	}

	@Override
	public Contract getContractByOneAutomobileId(int customerID) throws NotExistContractException{
		for (Contract contract : contractList) {
			if (contract.getCustomerID() != customerID) {
				continue;
			}
			if (!(contract.getProduct() instanceof Insurance)) {
				continue;
			}
			Insurance insurance = (Insurance) contract.getProduct();
			if (insurance.getInsuranceType() == InsuranceType.Automobile) {
				return contract.clone();
			}
		}
		throw new NotExistContractException();
	}

	@Override
	public ArrayList<Contract> getAllRequestingInsurance() {
		ArrayList<Contract> result = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Insurance
					&& contract.getContractStatus() == ContractStatus.ContractRequesting) {
				result.add(contract.clone());
			}
		}
		return result;
	}

	@Override
	public ArrayList<Contract> getAllNotRequestingInsurance() {
		ArrayList<Contract> result = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Insurance
					&& contract.getContractStatus() != ContractStatus.ContractRequesting) {
				result.add(contract.clone());
			}
		}
		return result;
	}

	@Override
	public ArrayList<Contract> getAllDefaultContract() {
		ArrayList<Contract> result = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -31);
		long caltime = cal.getTimeInMillis();
		Date date = new Date(caltime);
		
		for (Contract contract : contractList) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (dateFormat.parse(contract.getTerminationDate()).before(date)) {
					result.add(contract.clone());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<Contract> getAllRequestingLoan() {
		ArrayList<Contract> result = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Loan
					&& contract.getContractStatus() == ContractStatus.ContractRequesting) {
				result.add(contract.clone());
			}
		}
		return result;
	}

	@Override
	public ArrayList<Contract> getAllNotRequestingLoan() {
		ArrayList<Contract> result = new ArrayList<>();
		for (Contract contract : contractList) {
			if (contract.getProduct() instanceof Loan
					&& contract.getContractStatus() != ContractStatus.ContractRequesting) {
				result.add(contract.clone());
			}
		}
		return result;
	}

	@Override
	public Contract getRequestingLoan(int id) throws NotExistContractException {
		for (Contract contract : contractList) {
			if (contract.getId() == id) {
				if (contract.getProduct() instanceof Loan
						&& contract.getContractStatus() == ContractStatus.ContractRequesting) {
					return contract;
				} else {
					break;
				}
			}
		}
		throw new NotExistContractException();
	}

	@Override
	public ArrayList<Contract> getAllLoanContract() {
		ArrayList<Contract> contractList = new ArrayList<>();
		for (Contract contract : this.contractList) {
			if (contract.getProduct() instanceof Loan) {
				contractList.add(contract);
			}
		}
		return contractList;
	}

	@Override
	public Contract getLoanContract(int id) throws NotExistContractException {
		for (Contract contract : this.contractList) {
			if (contract.getId() == id) {
				if (contract.getProduct() instanceof Loan) {
					return contract;
				} else {
					throw new NotExistContractException();
				}
			}
		}
		throw new NotExistContractException();
	}

	@Override
	public Contract getNotRequestingLoan(int id) throws NotExistContractException {
		for (Contract contract : contractList) {
			if (contract.getId() == id) {
				if (contract.getProduct() instanceof Loan
						&& contract.getContractStatus() != ContractStatus.ContractRequesting) {
					return contract;
				} else {
					break;
				}
			}
		}
		throw new NotExistContractException();
	}

	@Override
	public Contract getNotRequestingLoanByCustomer(int id) throws NotExistContractException {
		for (Contract contract : contractList) {
			if (contract.getCustomerID() == id) {
				if (contract.getProduct() instanceof Loan
						&& contract.getContractStatus() != ContractStatus.ContractRequesting) {
					return contract;
				} else {
					break;
				}
			}
		}
		throw new NotExistContractException();
	}

	@Override
	public ArrayList<Contract> getAllApprovedByCustomer(int id) throws NotExistContractException {
		ArrayList<Contract> contractList = new ArrayList<>();
		for (Contract contract : this.contractList) {
			if (contract.getContractStatus() != ContractStatus.ContractRequesting && contract.getExpirationDate() != null) {
				contractList.add(contract);
			}
		}
		if (contractList.isEmpty()) {
			throw new NotExistContractException();
		}
		return contractList;
	}
}