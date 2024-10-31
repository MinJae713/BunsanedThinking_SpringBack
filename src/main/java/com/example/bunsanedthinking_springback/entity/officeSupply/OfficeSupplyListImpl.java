package com.example.bunsanedthinking_springback.entity.officeSupply;

import com.example.bunsanedthinking_springback.constants.DumyObjs;
import com.example.bunsanedthinking_springback.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */

//2024-06-02 김대현
//2024-06-04 김대현
public class OfficeSupplyListImpl implements OfficeSupplyList {

	private ArrayList<OfficeSupply> officeSupplyList;
	public static int index = 0;

	public OfficeSupplyListImpl(){
		officeSupplyList = new ArrayList<OfficeSupply>();
		OfficeSupply[] dumy = DumyObjs.DUMY_OFFICESUPPLY;
		for (OfficeSupply officeSupply : dumy)
			officeSupplyList.add(officeSupply);
	}

	/**
	 * 
	 * @param officeSupply
	 */
	public void add(OfficeSupply officeSupply){
		index++;
		String compound = OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER +""+ index;
		officeSupply.setId(Integer.parseInt(compound));
		officeSupplyList.add(officeSupply);
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException {
		for (OfficeSupply e : officeSupplyList) {
			if (e != null && e.getId() == id) {
				officeSupplyList.remove(e);
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
	public OfficeSupply get(int id) throws NotExistException{
		for (OfficeSupply e : officeSupplyList) {
			if (e != null && e.getId() == id) {
				return e.clone();
			}
		}
		throw new NotExistException();
	}

	/**
	 * 
	 * @param id
	 * @throws NotExistException 
	 */
	public void update(OfficeSupply officeSupply) throws NotExistException{
		for (int i = 0; i < officeSupplyList.size(); i++) {
			if (officeSupplyList.get(i).getId() == officeSupply.getId()) {
				officeSupplyList.set(i, officeSupply);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<OfficeSupply> getAll() {
		ArrayList<OfficeSupply> result = new ArrayList<>();
		for (OfficeSupply officeSupply : officeSupplyList) {
			result.add(officeSupply.clone());
		}
		return result;
	}

	@Override
	public int getTotalInventory() {
		int totalInventory = 0;
		for (OfficeSupply e : officeSupplyList) {
			totalInventory += e.getInventory();
		}
		return totalInventory;
	}
	
}