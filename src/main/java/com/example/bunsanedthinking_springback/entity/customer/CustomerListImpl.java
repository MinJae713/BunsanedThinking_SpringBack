package com.example.bunsanedthinking_springback.entity.customer;

import com.example.bunsanedthinking_springback.global.constants.DumyObjs;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	private ArrayList<Customer> customerList;

	private static int index = 0;

	public CustomerListImpl() {
		this.customerList = new ArrayList<Customer>();

		//	 	this.add(new Customer("카즈하", "010-1112-1112", "대학생", 27, Gender.Female ,"111111-1111111", "서대문구", 10000L, "신한", "1012-215-225937"));
		//		this.add(new Customer("장원영", "010-2223-2223", "유튜버", 23, Gender.Female, "222222-2222222", "송파구", 40000L, "우리", "1012-215-225937"));
		//		this.add(new Customer("권은비", "010-3334-3334", "물총장수", 25, Gender.Female, "333333-3333333", "마포구", 30000L, "국민", "1012-215-225937"));
		//		this.add(new Customer("이강인", "010-4445-4445", "축구선수", 23, Gender.Male, "444444-4444444", "종로구", 45000L, "하나", "1012-215-225937"));
		//		this.add(new Customer("오소름", "010-5556-5556", "영업사원", 42, Gender.Male, "555555-5555555", "강남구", 50000L, "우리", "1012-215-225937"));
		//		this.add(new Customer("최그램", "010-6667-6667", "파티플래너", 56, Gender.Male, "666666-6666666", "상록구", 25000L, "국민", "1012-215-225937"));
		Customer[] dumy = DumyObjs.DUMY_CUSTOMERS;
		for (Customer customer : dumy)
			this.add(customer);
		//		for (Customer customer : customerList) {
		//			System.out.println(customer.getName() + " " + customer.getId());
		//		}

	}

	/**
	 *
	 * @param customer
	 */
	public void add(Customer customer) {
//		String compound = Customer.CUSTOMER_SERIAL_NUMBER + "" + (++index);
//		customer.setId(Integer.parseInt(compound));
//		customerList.add(customer);
	}

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException {
		boolean result = customerList.removeIf(customer -> customer.getId() == id);
		if (!result) {
			throw new NotExistException();
		}
	}

	/**
	 *
	 * @param id
	 */
	public Customer get(int id) throws NotExistException {
		for (Customer customer : customerList) {
			if (customer.getId() == id) {
				return customer.clone();
			}
		}
		throw new NotExistException();
	}

	//	public void update(int id){
	//	 for (int i = 0; i < customerList.size(); i++) {
	//            if (customerList.get(i).getId() == id) {
	//                customerList.set(i, updatedCustomer);
	//                return;
	//            }
	//     }
	//	}
	@Override
	public void update(Customer customer) throws NotExistException {
		for (int i = 0; i < this.customerList.size(); i++) {
			if (customerList.get(i).getId() == customer.getId()) {
				customerList.set(i, customer);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> result = new ArrayList<>();
		for (Customer customer : customerList) {
			result.add(customer.clone());
		}
		return result;
	}

}
