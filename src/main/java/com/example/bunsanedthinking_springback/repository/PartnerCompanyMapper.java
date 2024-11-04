package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;

@Mapper
public interface PartnerCompanyMapper {
	List<PartnerCompanyVO> findByPartnerType_CustomerSupport(int partnerType);

	Optional<PartnerCompanyVO> findById_CustomerSupport(int id);
  PartnerCompany findById_PartnerCompany(@Param("id") int id);
}
