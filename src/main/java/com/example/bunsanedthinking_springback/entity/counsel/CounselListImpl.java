package com.example.bunsanedthinking_springback.entity.counsel;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public class CounselListImpl implements CounselList {

	private ArrayList<Counsel> counselList;
	private int index = 0;

	public CounselListImpl() {
		counselList = new ArrayList<Counsel>();
	}

	/**
	 *
	 * @param counsel
	 */
	public void add(Counsel counsel) {
//		String compound = Counsel.COUNSEL_SERIAL_NUMBER + "" + (++index);
//		counsel.setId(Integer.parseInt(compound));
//		counselList.add(counsel);
	}

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException {
		for (Counsel e : counselList) {
			if (e != null && e.getId() == id) {
				counselList.remove(e);
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
	public Counsel get(int id) throws NotExistException {
		for (Counsel e : counselList) {
			if (e != null && e.getId() == id) {
				return e.clone();
			}
		}
		throw new NotExistException();
	}

	public void update(Counsel counsel) throws NotExistException {
		for (int i = 0; i < counselList.size(); i++) {
			if (counselList.get(i).getId() == counsel.getId()) {
				counselList.set(i, counsel);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<Counsel> getAll() {
		ArrayList<Counsel> result = new ArrayList<>();
		for (Counsel counsel : counselList) {
			result.add(counsel.clone());
		}
		return result;
	}

}
