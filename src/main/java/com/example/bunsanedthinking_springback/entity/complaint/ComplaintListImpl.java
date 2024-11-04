package com.example.bunsanedthinking_springback.entity.complaint;

import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
//0605 ����
// clone�� �����ֵ��� �����ؾߵ�
public class ComplaintListImpl implements ComplaintList {

	private static int index;
	private ArrayList<Complaint> complaintList;

	public ComplaintListImpl(){
		ComplaintListImpl.index = 1;
		this.complaintList = new ArrayList<>();
	}

	/**
	 * 
	 * @param complaint
	 */
	public void add(Complaint complaint){
		String compose = "" + Complaint.COMPLAINT_SERIAL + ComplaintListImpl.index;
		complaint.setId(Integer.parseInt(compose));
		ComplaintListImpl.index++;
		this.complaintList.add(complaint);
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException{
		for (int i=0; i<complaintList.size(); i++) {
			if (complaintList.get(i).getId() == id) {
				complaintList.remove(i);
				return;
			}
		}
		throw new NotExistException();
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException 
	 */
	public Complaint get(int id) throws NotExistException{
		for (Complaint complaint : complaintList) {
			if (complaint.getId() == id) {
				return complaint.clone();
			}
		}
		throw new NotExistException();
	}

	/**
	 *
	 * @throws NotExistException 
	 */
	public void update(Complaint complaint) throws NotExistException{
		for (int i=0; i<complaintList.size(); i++) {
			if (complaintList.get(i).getId() == complaint.getId()) {
				complaintList.set(i, complaint);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<Complaint> getAllByCustomerId(int id) {
		ArrayList<Complaint> complaints= new ArrayList<>();
		for (Complaint complaint : complaintList) {
			if (complaint.getCustomerID() == id) {
				complaints.add(complaint.clone());
			}
		}
		return complaints;
	}

	@Override
	public ArrayList<Complaint> getAll() {
		return this.complaintList;
	}

	@Override
	public ArrayList<Complaint> getAllUnprocessedComplaint() {
		ArrayList<Complaint> complaints= new ArrayList<>();
		for (Complaint complaint : complaintList) {
			if (complaint.getProcessStatus() == ComplaintProcessStatus.Unprocessed) {
				complaints.add(complaint.clone());
			}
		}
		return complaints;
	}

	@Override
	public ArrayList<Complaint> getAllProcessedComplant() {
		ArrayList<Complaint> complaints= new ArrayList<>();
		for (Complaint complaint : complaintList) {
			if (complaint.getProcessStatus() == ComplaintProcessStatus.Completed) {
				complaints.add(complaint.clone());
			}
		}
		return complaints;
	}
}