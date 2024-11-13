package com.example.bunsanedthinking_springback.entity.accidentHistory;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */

//2024-06-04 김대현
public class AccidentHistoryListImpl implements AccidentHistoryList {

	private static int index = 0;
	
	private ArrayList<AccidentHistory> accidentHistoryList;

	public AccidentHistoryListImpl(){
		accidentHistoryList = new ArrayList<AccidentHistory>(); 
	}

	/**
	 * 
	 * @param accidentHistory
	 */
	public void add(AccidentHistory accidentHistory){
//		String compound = AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "" + (++index);
//		accidentHistory.setId(Integer.parseInt(compound));
//		accidentHistoryList.add(accidentHistory);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id){
		for (AccidentHistory e : accidentHistoryList) {
			if (e != null && e.getId() == id) {
				accidentHistoryList.remove(e);
				break;
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public AccidentHistory get(int id){
		for (AccidentHistory e : accidentHistoryList) {
			if (e != null && e.getId() == id) {
				return e.clone();
			}
		}
		return null;
	}

	/**
	 *
	 */
	public void update(AccidentHistory accidentHistory){
		for (int i = 0; i < accidentHistoryList.size(); i++) {
			if (accidentHistoryList.get(i).getId() == accidentHistory.getId()) {
				accidentHistoryList.set(i, accidentHistory);
			}
		}
	}

}