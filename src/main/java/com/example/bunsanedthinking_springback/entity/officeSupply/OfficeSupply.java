package com.example.bunsanedthinking_springback.entity.officeSupply;


import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
@NoArgsConstructor
public class OfficeSupply implements Cloneable {

	private int id;
	private int inventory;
	private String name;
	private int totalInventory;
	private String description;
	private int departmentId;
	public static final int OFFICESUPPLY_SERIAL_NUMBER = 920;
	
	
	public OfficeSupply(String name, String description, int inventory){
		this.setName(name);
		this.setDescription(description);
		this.setInventory(inventory);
	}

	public OfficeSupplyVO getVO() {
		return new OfficeSupplyVO(id, inventory,
				name, totalInventory, description,
				departmentId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(int totalInventory) {
		this.totalInventory = totalInventory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public OfficeSupply clone() {
		OfficeSupply officeSupply = new OfficeSupply(getName(), getDescription(), getInventory());
		officeSupply.setId(getId());
		officeSupply.setTotalInventory(getTotalInventory());
		return officeSupply;
		
	}
	
}