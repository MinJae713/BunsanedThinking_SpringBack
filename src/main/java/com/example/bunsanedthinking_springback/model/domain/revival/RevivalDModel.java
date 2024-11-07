package com.example.bunsanedthinking_springback.model.domain.revival;

import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.RevivalMapper;
import com.example.bunsanedthinking_springback.vo.RevivalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevivalDModel {
    @Autowired
    private RevivalMapper revivalMapper;
    @Autowired
    private ContractDModel contractDModel;
    public Revival getById(int id) {
        return revivalMapper.
                getById_Customer(id).
                orElse(null).
                getEntity(contractDModel.getById(id));
    }
    public List<Revival> getAll() {
        List<Revival> revivals = new ArrayList<Revival>();
        revivalMapper.getAll_ContractManagement().
                stream().forEach(
                        e -> revivals.add(getById(e.getContract_id()))
                );
        return revivals;
    }
    public int getMaxId() {
        return revivalMapper.getMaxId();
    }
    public void add(RevivalVO revivalVO) {
        revivalMapper.insert(revivalVO);
    }
    public void update(RevivalVO revivalVO) {
        revivalMapper.update(revivalVO);
    }
    public void delete(int id) {
        revivalMapper.deleteById(id);
    }
}
