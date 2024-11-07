package com.example.bunsanedthinking_springback.entity.insuranceMoney;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
public class InsuranceMoneyListImpl implements InsuranceMoneyList {

	private static int index = 0;

	private ArrayList<InsuranceMoney> insuranceMoneyList;

	public InsuranceMoneyListImpl() {
		this.insuranceMoneyList = new ArrayList<>();
	}

	/**
	 *
	 * @param insuranceMoney
	 */
	public void add(InsuranceMoney insuranceMoney) {
		index++;
		insuranceMoney.setId(index);
		this.insuranceMoneyList.add(insuranceMoney);
	}

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException {
		boolean result = this.insuranceMoneyList.removeIf(insuranceMoney -> insuranceMoney.getId() == id);
		if (!result) {
			throw new NotExistException();
		}
	}

	/**
	 *
	 * @param id
	 */
	public InsuranceMoney get(int id) throws NotExistException {
		for (InsuranceMoney insuranceMoney : this.insuranceMoneyList) {
			if (insuranceMoney.getId() == id) {
				return insuranceMoney.clone();
			}
		}
		throw new NotExistException();
	}

	/**
	 *
	 * @throws NotExistException
	 */
	public void update(InsuranceMoney insuranceMoney) throws NotExistException {
		for (int i = 0; i < this.insuranceMoneyList.size(); i++) {
			if (insuranceMoneyList.get(i).getId() == insuranceMoney.getId()) {
				insuranceMoneyList.set(i, insuranceMoney);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<InsuranceMoney> getAll() {
		ArrayList<InsuranceMoney> result = new ArrayList<>();
		for (InsuranceMoney insuranceMoney : this.insuranceMoneyList) {
			result.add(insuranceMoney.clone());
		}
		return result;
	}

	@Override
	public ArrayList<InsuranceMoney> getAllUnprocessed() {
		ArrayList<InsuranceMoney> result = new ArrayList<>();
		for (InsuranceMoney insuranceMoney : this.insuranceMoneyList) {
			if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Unprocessed) {
				result.add(insuranceMoney.clone());
			}
		}
		return result;
	}

	@Override
	public ArrayList<InsuranceMoney> getAllProcessed() {
		ArrayList<InsuranceMoney> result = new ArrayList<>();
		for (InsuranceMoney insuranceMoney : this.insuranceMoneyList) {
			if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed) {
				result.add(insuranceMoney.clone());
			}
		}
		return result;
	}

}
