package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PartnerCompanyMapper {
    PartnerCompany findById_PartnerCompany(@Param("id") int id);
}
