package com.example.bunsanedthinking_springback.model.entityModel.officeSupply;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.repository.OfficeSupplyMapper;
import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeSupplyEntityModel {
	@Autowired
	private OfficeSupplyMapper officeSupplyMapper;

	public OfficeSupply getById(int id) {
		OfficeSupplyVO officeSupplyVO = officeSupplyMapper.getById(id).orElse(null);
		if (officeSupplyVO == null)
			return null;
		return officeSupplyVO.getEntity();
	}

	public List<OfficeSupply> getAll() {
		List<OfficeSupply> officeSupplies = new ArrayList<OfficeSupply>();
		officeSupplyMapper.getAll()
			.forEach(e -> officeSupplies.add(e.getEntity()));
		return officeSupplies;
	}

	public Integer getMaxId() {
		return officeSupplyMapper.getMaxId();
	}

	public void add(OfficeSupply officeSupply) {
		officeSupplyMapper.insert(officeSupply.findVO());
	}

	public void update(OfficeSupply officeSupply) {
		officeSupplyMapper.update(officeSupply.findVO());
	}

	public void delete(int id) {
		if (getById(id) == null) return;
		officeSupplyMapper.delete(id);
	}
	public int getTotalInventory(){
		return officeSupplyMapper.getTotalInventory();
	}
}
