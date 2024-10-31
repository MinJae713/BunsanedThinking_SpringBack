package com.example.bunsanedthinking_springback.entity.diseaseHistory;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */

//2024-06-04 김대현
public class DiseaseHistoryListImpl implements DiseaseHistoryList {

	private static int index = 0;
	private ArrayList<DiseaseHistory> diseaseHistoryList;

	public DiseaseHistoryListImpl() {
		diseaseHistoryList = new ArrayList<DiseaseHistory>();
	}

	/**
	 * 
	 * @param diseaseHistory
	 */
	public void add(DiseaseHistory diseaseHistory) {
		String compound = DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "" + (++index);
		diseaseHistory.setId(Integer.parseInt(compound));
		diseaseHistoryList.add(diseaseHistory);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) {
		for (DiseaseHistory e : diseaseHistoryList) {
			if (e != null && e.getId() == id) {
				diseaseHistoryList.remove(e);
				break;
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public DiseaseHistory get(int id) {
		for (DiseaseHistory e : diseaseHistoryList) {
			if (e != null && e.getId() == id) {
				return e.clone();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public void update(DiseaseHistory diseaseHistory) {
		for (int i = 0; i < diseaseHistoryList.size(); i++) {
			if (diseaseHistoryList.get(i).getId() == diseaseHistory.getId()) {
				diseaseHistoryList.set(i, diseaseHistory);
			}
		}
	}

}