package com.example.bunsanedthinking_springback.model.customer;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetailList;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositPath;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.endorsment.EndorsementList;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.recontract.RecontractList;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.revival.RevivalList;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.entity.termination.TerminationList;
import com.example.bunsanedthinking_springback.exception.*;
import com.example.bunsanedthinking_springback.repository.DiseaseHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerModel {
	@Autowired
	private DiseaseHistoryMapper diseaseHistoryMapper;
	// mapper는 예시 - 구현 이후 지우시오(xml의 select, mapper의 getTst(), model의 test)
	// DB 열이랑 entity 필드 이름만 맞춰두면 잘 돌아갑니다ㅏㅏ (이름 틀리면 값 못갖고옴)
	public List<DiseaseHistory> test() {
		List<DiseaseHistory> diseaseHistories = diseaseHistoryMapper.getTst();
		return diseaseHistories;
	}
	public void applyEndorsement(ContractList contractList, EndorsementList endorsementList, int index,
								 Contract contract) throws NotExistContractException {
		contract.setContractStatus(ContractStatus.EndorsementRequesting);
		contractList.update(contract);
		Endorsement endorsement = new Endorsement(contract);
		endorsementList.add(endorsement);
		endorsement.setPaymentDate(index);

	}

	public void applyInsuranceRevival(ContractList contractList, RevivalList revivalList, Contract contract,
									  Customer customer) throws NotExistContractException, NotExistTerminatedContract {
		if (contract.getContractStatus() == ContractStatus.Terminating && contract.getExpirationDate() != null) {
			contract.setContractStatus(ContractStatus.RevivalRequesting);
			contractList.update(contract);
			Revival revival = new Revival(contract);
			revivalList.add(revival);
		} else {
			throw new NotExistTerminatedContract();
		}
	}

	public void applyInsuranceTermination(ContractList contractList, TerminationList terminationList, Contract contract,
										  Customer customer) throws NotExistContractException, NotExistMaintainedContract {
		if (contract.getContractStatus() == ContractStatus.Maintaining) {
			contract.setContractStatus(ContractStatus.TerminationRequesting);
			contractList.update(contract);
			Termination termination = new Termination(contract);
			terminationList.add(termination);
		} else {
			throw new NotExistMaintainedContract();
		}
	}

	public void applyRecontract(ContractList contractList, RecontractList recontractList, Contract contract,
								Customer customer) throws NotExistContractException, NotExistExpiredContract {
		if (contract.getContractStatus() == ContractStatus.Maturing) {
			contract.setContractStatus(ContractStatus.RecontractRequesting);
			contractList.update(contract);
			Recontract recontract = new Recontract(contract);
			recontractList.add(recontract);
		} else {
			throw new NotExistExpiredContract();
		}
	}
	public void payInsurancefee(Customer customer, Contract contract, int money, DepositPath path,
			DepositDetailList depositDetailList) {
		DepositDetail depositDetail = new DepositDetail(customer.getName(), contract.getId(), money, path);
		depositDetailList.add(depositDetail);
		contract.getDepositDetailList().add(depositDetail);
	}
	public Insurance viewInsuranceProductList(ProductList productList, int id) throws NotExistException {
		ArrayList<Insurance> insuranceList = productList.getAllInsurance();
		for (Insurance insurance : insuranceList) {
			if (insurance.getId() == id) {
				return insurance;
			}
		}
		throw new NotExistException();
	}
	public Loan viewLoanProductList(ProductList productList, int id) throws NotExistException {
		ArrayList<Loan> loanList = productList.getAllLoan();
		for (Loan loan : loanList) {
			if (loan.getId() == id) {
				return loan;
			}
		}
		throw new NotExistException();
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {
		return customerList.get(id);
	}
	public ArrayList<Insurance> getAllInsurance(ProductList productList) {
		return productList.getAllInsurance();
	}
	public ArrayList<Insurance> getAllDiseaseInsurance(ProductList productList) {
		return productList.getAllDiseaseInsurance();
	}
	public ArrayList<Insurance> getAllInjuryInsurance(ProductList productList) {
		return productList.getAllInjuryInsurance();
	}
	public ArrayList<Insurance> getAllAutomobileInsurance(ProductList productList) {
		return productList.getAllAutomobileInsurance();
	}
	public Insurance get(int id, ProductList productList) throws NotExistException {
		return (Insurance) productList.get(id);
	}
	public ArrayList<Loan> getAllLoan(ProductList productList) {
		return productList.getAllLoan();
	}
	public ArrayList<Loan> getAllCollateralLoan(ProductList productList) {
		return productList.getAllCollateralLoan();
	}
	public ArrayList<Loan> getAllFixedDepositLoan(ProductList productList) {
		return productList.getAllFixedDepositLoan();
	}
	public ArrayList<Loan> getAllInsuranceContractLoan(ProductList productList) {
		return productList.getAllInsuranceContractLoan();
	}
	public Loan getLoan(ProductList productList, int id) throws NotExistException {
		return productList.getLoan(id);
	}
	public ArrayList<Contract> getAllApprovedByCustomer(ContractList contractList, int id) throws NotExistContractException {
		return contractList.getAllApprovedByCustomer(id);
	}
	public Insurance getInsurance(ProductList productList, int id) throws NotExistException {
		return productList.getInsurance(id);
	}
	public ArrayList<Contract> getAllByCustomer(ContractList contractList, int id) throws NotExistContractException {
		return contractList.getAllByCustomer(id);
	}
	public ArrayList<Contract> getAllAutomobileInsuranceContract(ContractList contractList) throws NotExistContractException {
		return contractList.getAllAutomobileInsuranceContract();
	}
	public ArrayList<Contract> getAllInjuryInsuranceContract(ContractList contractList) throws NotExistContractException {
		return contractList.getAllInjuryInsuranceContract();
	}
	public ArrayList<Contract> getAllDiseaseInsuranceContract(ContractList contractList) throws NotExistContractException {
		return contractList.getAllDiseaseInsuranceContract();
	}
	public ArrayList<Contract> getContractByProductId(ContractList contractList, int id) {
		return contractList.getContractByproductId(id);
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return contractList.get(id);
	}
	public Contract getAutomobileByMember(ContractList contractList, int id) throws NotExistContractException {
		return contractList.getAutomobileByMember(id);
	}
	public ArrayList<Accident> getAllByCustomer(AccidentList accidentList, int id) {
		return accidentList.getAllByCustomer(id);
	}
	public Accident get(AccidentList accidentList, int id) throws NotExistException {
		return accidentList.get(id);
	}
	public ArrayList<Complaint> getAllByCustomerId(ComplaintList complaintList, int id) {
		return complaintList.getAllByCustomerId(id);
	}
	public Complaint get(ComplaintList complaintList, int id) throws NotExistException {
		return complaintList.get(id);
	}
}
