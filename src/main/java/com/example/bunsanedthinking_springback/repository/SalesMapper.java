package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.SalesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SalesMapper {
	SalesVO get_SalesModel(int id);

	void updateEvaluate_SalesModel(int id, int evaluate);

	ArrayList<SalesVO> getAll_SalesModel();

	void update_SalesModel(SalesVO salesVO);

	public int getMaxId();
	public void insert(SalesVO salesVO);
	public void deleteById(int id);
}
