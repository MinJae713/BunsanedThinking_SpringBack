package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;

@Mapper
public interface PartnerCompanyMapper {
	List<PartnerCompanyVO> findByPartnerType_CustomerSupport(int partnerType);

	Optional<PartnerCompanyVO> findById_CustomerSupport(int id);
}
