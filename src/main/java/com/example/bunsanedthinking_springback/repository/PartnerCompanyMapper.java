package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PartnerCompanyMapper {
	List<PartnerCompanyVO> findByPartnerType_CustomerSupport(int partnerType);

	Optional<PartnerCompanyVO> findById_CustomerSupport(int id);

  	PartnerCompanyVO findById_PartnerCompany(@Param("id") int id);

	void insert_CompensationPlanning(PartnerCompanyVO partnerCompanyVO);

	void update_CompensationPlanning(PartnerCompanyVO partnerCompanyVO);

	List<PartnerCompanyVO> getAll_CompensationPlanning();

	void delete_CompensationPlanning(int id);

	Integer getMaxId_CompensationPlanning();
}
