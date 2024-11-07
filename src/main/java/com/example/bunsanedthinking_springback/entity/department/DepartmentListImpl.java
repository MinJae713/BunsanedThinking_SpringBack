package com.example.bunsanedthinking_springback.entity.department;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.constants.DumyObjs;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */

//2024-06-02 김대현
public class DepartmentListImpl implements DepartmentList {

	private ArrayList<Department> departmentList;
	public static int DepartmentId = 0;

	public DepartmentListImpl() {
		departmentList = new ArrayList<Department>();
		Department[] dumy = DumyObjs.DUMY_DEPARTMENTS;
		for (Department department : dumy)
			this.add(department);
	}

	/**
	 *
	 * @param department
	 */
	public void add(Department department) {
		DepartmentId++;
		String compound = Department.DepartmentSerialNum + "" + DepartmentId;
		department.setId(Integer.parseInt(compound));
		departmentList.add(department);
	}

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException {
		for (Department e : departmentList) {
			if (e != null && e.getId() == id) {
				departmentList.remove(e);
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
	public Department get(int id) throws NotExistException {
		for (Department e : departmentList) {
			if (e != null && e.getId() == id) {
				return e.clone();
			}
		}
		throw new NotExistException();
	}

	public void update(Department department) throws NotExistException {
		for (int i = 0; i < departmentList.size(); i++) {
			if (departmentList.get(i).getId() == department.getId()) {
				departmentList.set(i, department);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<Department> getAll() {
		ArrayList<Department> result = new ArrayList<>();
		for (Department department : departmentList) {
			result.add(department.clone());
		}
		return result;
	}

}
