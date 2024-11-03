package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AccidentVO;

@Mapper
public interface AccidentMapper {
	Optional<AccidentVO> findByID_CustomerSupport(int id);

	void update_CustomerSupport(AccidentVO accidentVO);

	List<AccidentVO> getAll_CustomerSupport();

	List<AccidentVO> findByProcessStatus_CustomerSupport(int processStatus);
}
