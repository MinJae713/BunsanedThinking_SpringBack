package com.example.bunsanedthinking_springback.entity.accident;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
public class AccidentListImpl implements AccidentList {

	private ArrayList<Accident> accidentList;
	private static int index;

	public AccidentListImpl() {
		this.accidentList = new ArrayList<>();
		index = 1;
	}

	/**
	 *
	 * @param accident
	 */
	public int add(Accident accident) {
//		String compose = "" + Accident.ACCIDENT_SERIAL + AccidentListImpl.index++;
//		accident.setId(Integer.parseInt(compose));
//		accidentList.add(accident);
		return accident.getId();
	}

	/**
	 *
	 * @param id
	 */
	public void delete(int id) throws NotExistException {

	}

	/**
	 *
	 * @param id
	 */
	public Accident get(int id) throws NotExistException {
		for (Accident accident : accidentList) {
			if (accident.getId() == id) {
				return accident;
			}
		}
		throw new NotExistException();
	}

	/**
	 *
	 */
	public void update(Accident accident) throws NotExistException {

	}

	@Override
	public ArrayList<Accident> getAll() {
		return accidentList;
	}

	@Override
	public ArrayList<Accident> getAllUnprocessedReport() {
		ArrayList<Accident> accidents = new ArrayList<>();
		for (Accident accident : accidentList) {
			if (accident.getProcessStatus() == AccidentProcessStatus.Unprocessed) {
				accidents.add(accident);
			}
		}
		return accidents;
	}

	@Override
	public ArrayList<Accident> getAllCompletedReport() {
		ArrayList<Accident> accidents = new ArrayList<>();
		for (Accident accident : accidentList) {
			if (accident.getProcessStatus() == AccidentProcessStatus.Completed) {
				accidents.add(accident);
			}
		}
		return accidents;
	}

	@Override
	public ArrayList<Accident> getAllByCustomer(int id) {
		ArrayList<Accident> accidents = new ArrayList<>();
		for (Accident accident : accidentList) {
			if (accident.getCustomerID() == id) {
				accidents.add(accident);
			}
		}
		return accidents;
	}

}
