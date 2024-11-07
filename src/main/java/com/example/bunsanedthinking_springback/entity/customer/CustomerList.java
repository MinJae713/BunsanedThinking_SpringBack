package com.example.bunsanedthinking_springback.entity.customer;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import java.util.ArrayList;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
public interface CustomerList {

	/**
	 *
	 * @param customer
	 */
	public void add(Customer customer);

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
	public Customer get(int id) throws NotExistException;

	/**
	 *
	 * @param customer
	 * @throws NotExistException
	 */
	public void update(Customer customer) throws NotExistException;

	public ArrayList<Customer> getAll();

}
