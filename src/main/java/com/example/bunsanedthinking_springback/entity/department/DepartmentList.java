package com.example.bunsanedthinking_springback.entity.department;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public interface DepartmentList {

	/**
	 *
	 * @param department
	 */
	public void add(Department department);

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
	public Department get(int id) throws NotExistException;

	public void update(Department department) throws NotExistException;

	public ArrayList<Department> getAll();

}
