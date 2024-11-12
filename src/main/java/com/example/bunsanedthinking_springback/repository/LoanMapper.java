package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.LoanVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
    public List<LoanVO> getAll();
	public Optional<LoanVO> getById(int id);
	public Integer getMaxId();
	public void insert(LoanVO loanVO);
	public void update(LoanVO loanVO);
	public void deleteById(int id);
}
