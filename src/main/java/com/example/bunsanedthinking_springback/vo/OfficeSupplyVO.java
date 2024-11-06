package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeSupplyVO {
	private int id;
	private int inventory;
	private String name;
	private int total_inventory;
	private String description;
	private int department_id;

	public OfficeSupply getEntity() {
		OfficeSupply officeSupply = new OfficeSupply();
		officeSupply.setId(id);
		officeSupply.setInventory(inventory);
		officeSupply.setName(name);
		officeSupply.setTotalInventory(total_inventory);
		officeSupply.setDescription(description);
		officeSupply.setDepartmentId(department_id);
		return officeSupply;
	}
}
