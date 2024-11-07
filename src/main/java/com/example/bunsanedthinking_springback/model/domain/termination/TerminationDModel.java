package com.example.bunsanedthinking_springback.model.domain.termination;

import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.TerminationMapper;
import com.example.bunsanedthinking_springback.vo.TerminationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminationDModel {
    @Autowired
    private TerminationMapper terminationMapper;
    @Autowired
    private ContractDModel contractDModel;
    public Termination getById(int id) {
        return terminationMapper.
                getById_Customer(id).
                orElse(null).
                getEntity(contractDModel.getById(id));
    }
    public List<Termination> getAll() {
        List<Termination> terminations = new ArrayList<>();
        terminationMapper.getAll_ContractManagement().
                stream().forEach(
                        e -> terminations.add(getById(e.getContract_id()))
                );
        return terminations;
    }
    public int getMaxId() {
        return terminationMapper.getMaxId();
    }
    public void add(TerminationVO terminationVO) {
        terminationMapper.insert(terminationVO);
    }
    public void update(TerminationVO terminationVO) {
        terminationMapper.update(terminationVO);
    }
    public void delete(int id) {
        terminationMapper.deleteById(id);
    }
}
