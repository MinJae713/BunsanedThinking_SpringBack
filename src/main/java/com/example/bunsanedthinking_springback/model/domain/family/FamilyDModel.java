package com.example.bunsanedthinking_springback.model.domain.family;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.repository.FamilyMapper;
import com.example.bunsanedthinking_springback.vo.FamilyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyDModel {
    @Autowired
    private FamilyMapper familyMapper;
    public Family getById(int id) {
        return familyMapper.getById(id).orElse(null).getEntity();
    }
    public List<Family> getAll() {
        List<Family> families = new ArrayList<Family>();
        familyMapper.getAll().stream().forEach(e -> families.add(e.getEntity()));
        return families;
    }
    public int getMaxId() {
        return familyMapper.getMaxId_HumanResource();
    }
    public void add(FamilyVO familyVO) {
        familyMapper.insert_HumanResource(familyVO);
    }
    public void update(FamilyVO familyVO) {
        familyMapper.update(familyVO);
    }
    public void delete(int id) {
        familyMapper.delete(id);
    }
}
