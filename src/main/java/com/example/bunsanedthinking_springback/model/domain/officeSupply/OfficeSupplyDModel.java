package com.example.bunsanedthinking_springback.model.domain.officeSupply;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.repository.OfficeSupplyMapper;
import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeSupplyDModel {
    @Autowired
    private OfficeSupplyMapper officeSupplyMapper;
    public OfficeSupply getById(int id) {
        return officeSupplyMapper.findById_OfficeSupply(id).getEntity();
    }
    public List<OfficeSupply> getAll() {
        List<OfficeSupply> officeSupplies = new ArrayList<OfficeSupply>();
        officeSupplyMapper.getAll_OfficeSupply().stream().forEach(e -> officeSupplies.add(e.getEntity()));
        return officeSupplies;
    }
    public int getMaxId() {
        return officeSupplyMapper.getMaxId_Administrative();
    }
    public void add(OfficeSupplyVO officeSupplyVO) {
        officeSupplyMapper.insert_OfficeSupply(officeSupplyVO);
    }
    public void update(OfficeSupplyVO officeSupplyVO) {
        officeSupplyMapper.update(officeSupplyVO);
    }
    public void delete(int id) {
        officeSupplyMapper.delete_OfficeSupply(id);
    }
}
