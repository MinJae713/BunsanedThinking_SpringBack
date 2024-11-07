package com.example.bunsanedthinking_springback.model.domain.contract;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.model.domain.compensationDetail.CompensationDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.depositDetail.DepositDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.insuranceMoney.InsuranceMoneyDModel;
import com.example.bunsanedthinking_springback.model.domain.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.product.ProductDModel;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractDModel {
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private InsuranceMoneyDModel insuranceMoneyDModel;
    @Autowired
    private CompensationDetailDModel compensationDetailDModel;
    @Autowired
    private DepositDetailDModel depositDetailDModel;
    @Autowired
    private PaymentDetailDModel paymentDetailDModel;
    @Autowired
    private ProductDModel productDModel;
    public Contract getById(int id) {
        ContractVO contractVO = contractMapper.getById_Customer(id).orElse(null);
        ArrayList<InsuranceMoney> insuranceMonies = new ArrayList<InsuranceMoney>();
        ArrayList<CompensationDetail> compensationDetails = new ArrayList<CompensationDetail>();
        ArrayList<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
        ArrayList<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();

        insuranceMoneyDModel.getAll().stream().filter(e -> e.getContractId() == id).forEach(e -> insuranceMonies.add(e));
        compensationDetailDModel.getAll().stream().filter(e -> e.getContractId() == id).forEach(e -> compensationDetails.add(e));
        depositDetailDModel.getAll().stream().filter(e -> e.getContractId() == id).forEach(e -> depositDetails.add(e));
        paymentDetailDModel.getAll().stream().filter(e -> e.getContractId() == id).forEach(e -> paymentDetails.add(e));
        Product product = productDModel.getById(contractVO.getProduct_id());
        return new Contract(insuranceMonies, compensationDetails, depositDetails, paymentDetails, product, contractVO);
    }
    public List<Contract> getAll() {
        List<Contract> contracts = new ArrayList<Contract>();
        contractMapper.getAll_Customer().stream().forEach(e -> contracts.add(getById(e.getId())));
        return contracts;
    }
    public int getMaxId() {
        return contractMapper.getMaxId_SalesModel();
    }
    public void add(ContractVO contractVO) {
        contractMapper.insert_SalesModel(contractVO);
    }
    public void update(ContractVO contractVO) {
        contractMapper.update(contractVO);
    }
    public void delete(int id) {
        contractMapper.deleteById(id);
    }
}
