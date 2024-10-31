package com.example.bunsanedthinking_springback.entity.surgeryHistory;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
//2024-06-04 김대현
public class SurgeryHistoryListImpl implements SurgeryHistoryList {
	
	private static int index = 0;

	private ArrayList<SurgeryHistory> surgeryHistoryList;

	public SurgeryHistoryListImpl(){
		surgeryHistoryList = new ArrayList<SurgeryHistory>();
	}

	/**
	 * 
	 * @param surgeryHistory
	 */
	public void add(SurgeryHistory surgeryHistory){
		String compound = SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "" +(++index);
		surgeryHistory.setId(Integer.parseInt(compound));
		surgeryHistoryList.add(surgeryHistory);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id){
		for (SurgeryHistory e : surgeryHistoryList) {
			if (e != null && e.getId() == id) {
				surgeryHistoryList.remove(e);
				break;
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public SurgeryHistory get(int id){
		for (SurgeryHistory e : surgeryHistoryList) {
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
	public void update(SurgeryHistory surgeryHistory){
		for (int i = 0; i < surgeryHistoryList.size(); i++) {
			if (surgeryHistoryList.get(i).getId() == surgeryHistory.getId()) {
				surgeryHistoryList.set(i, surgeryHistory);
			}
		}
	}

}