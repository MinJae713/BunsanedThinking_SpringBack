package com.example.bunsanedthinking_springback.entity.officeSupply;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:43
 */

//2024-06-02 源����쁽
public interface OfficeSupplyList {

	/**
	 *
	 * @param officeSupply
	 */
	public void add(OfficeSupply officeSupply);

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException;

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public OfficeSupply get(int id) throws NotExistException;

	public void update(OfficeSupply officeSupply) throws NotExistException;

	public ArrayList<OfficeSupply> getAll();

	public int getTotalInventory();
}
