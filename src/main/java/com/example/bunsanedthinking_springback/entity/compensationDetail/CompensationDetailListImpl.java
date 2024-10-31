package com.example.bunsanedthinking_springback.entity.compensationDetail;

import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public class CompensationDetailListImpl implements CompensationDetailList {

	private static int index = 0;
	
	private ArrayList<CompensationDetail> compensationDetailList;

	public CompensationDetailListImpl(){
		compensationDetailList = new ArrayList<CompensationDetail>();
	}

	/**
	 * 
	 * @param compensationDetail
	 */
	public void add(CompensationDetail compensationDetail){
		index++;
		compensationDetail.setId(index);
		this.compensationDetailList.add(compensationDetail);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) throws NotExistException {
		for (CompensationDetail compensationDetail : this.compensationDetailList) {
			if (compensationDetail.getId() == id) {
				compensationDetailList.remove(compensationDetail);
				return;
			}
		}
		throw new NotExistException();
	}

	/**
	 * 
	 * @param id
	 */
	public CompensationDetail get(int id) throws NotExistException {
		for (CompensationDetail compensationDetail : this.compensationDetailList) {
			if (compensationDetail.getId() == id) {
				return compensationDetail.clone();
			}
		}
		throw new NotExistException();
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException 
	 */
	public void update(CompensationDetail compensationDetail) throws NotExistException{
		for (int i=0; i<compensationDetailList.size(); i++) {
			if (compensationDetailList.get(i).getId() == compensationDetail.getId()) {
				compensationDetailList.set(i, compensationDetail);
				return;
			}
		}
		throw new NotExistException();
	}

}