package com.example.bunsanedthinking_springback.entity.depositDetail;

import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public class DepositDetailListImpl implements DepositDetailList {

	private static int index = 0;
	private ArrayList<DepositDetail> depositDetailList;

	public DepositDetailListImpl(){
		this.depositDetailList = new ArrayList<>();
	}

	/**
	 * 
	 * @param depositDetail
	 */
	public void add(DepositDetail depositDetail) {
		String compose = "" + DepositDetail.DEPOSIT_DETAIL_SERIAL + DepositDetailListImpl.index;
		depositDetail.setId(Integer.parseInt(compose));
		DepositDetailListImpl.index++;
		this.depositDetailList.add(depositDetail);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id){

	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException
	 */
	public DepositDetail get(int id) throws NotExistException{
		for (DepositDetail depositDetail : depositDetailList) {
			if (depositDetail.getId() == id) {
				return depositDetail.clone();
			}
		}
		throw new NotExistException();
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException 
	 */
	public void update(DepositDetail depositDetail) throws NotExistException{
		for (int i=0; i<depositDetailList.size(); i++) {
			if (depositDetailList.get(i).getId() == depositDetail.getId()) {
				depositDetailList.set(i, depositDetail);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<DepositDetail> getAllDepositDetail() {
		ArrayList<DepositDetail> depositDetails = new ArrayList<>();
		for (DepositDetail depositDetail : depositDetailList) {
			depositDetails.add(depositDetail.clone());
		}
		return depositDetails;
	}

}