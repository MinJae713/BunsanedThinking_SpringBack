package com.example.bunsanedthinking_springback.entity.officeSupply;


/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
//2024-06-02 김대현
//2024-06-04 김대현
public class OfficeSupply implements Cloneable {

	private int id;
	private int inventory;
	private String name;
	private int totalInventory;
	private String explain;
	public static final int OFFICESUPPLY_SERIAL_NUMBER = 920;
	
	
	public OfficeSupply(String name, String explain, int inventory){
		this.setName(name);
		this.setExplain(explain);
		this.setInventory(inventory);
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

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public OfficeSupply clone() {
		OfficeSupply officeSupply = new OfficeSupply(getName(), getExplain(), getInventory());
		officeSupply.setId(getId());
		officeSupply.setTotalInventory(getTotalInventory());
		return officeSupply;
		
	}
	
}