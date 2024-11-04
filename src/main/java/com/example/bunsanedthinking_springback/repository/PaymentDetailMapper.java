package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDetailMapper {
    public int getCount_Compensation();
    public int getLastId_Compensation();

    public void add_Compensation(PaymentDetailVO paymentDetailVO);
}
