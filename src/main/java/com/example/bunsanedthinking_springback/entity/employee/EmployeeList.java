package com.example.bunsanedthinking_springback.entity.employee;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public interface EmployeeList {

	/**
	 *
	 * @param employee
	 */
	public void add(Employee employee);

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
	public Employee get(int id) throws NotExistException;

	public void update(Employee employee) throws NotExistException;

	public ArrayList<Employee> getAll();

	public Sales getSales(int id) throws NotExistException;

}
