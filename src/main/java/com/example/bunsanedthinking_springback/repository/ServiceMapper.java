package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ServiceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceMapper {
    public List<ServiceVO> getAllByProductId_Customer(int id);
}
