package com.example.bunsanedthinking_springback.entity.officeSupply;


import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

	public OfficeSupplyVO findVO() {
		return new OfficeSupplyVO(id, inventory,
				name, totalInventory, description,
				departmentId);
	}

	public OfficeSupply clone() {
		OfficeSupply officeSupply = new OfficeSupply(getName(), getDescription(), getInventory());
		officeSupply.setId(getId());
		officeSupply.setTotalInventory(getTotalInventory());
		return officeSupply;
		
	}
	
}