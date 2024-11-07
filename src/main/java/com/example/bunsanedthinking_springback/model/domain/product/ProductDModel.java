package com.example.bunsanedthinking_springback.model.domain.product;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDModel {
    @Autowired
    private ProductMapper productMapper;
    public Product getById(int id) {
        // 추상타입이라 반환 불가
        return null;
    }
    public List<Product> getAll() {
        // 추상타입이라 반환 불가
        return null;
    }
    public int getMaxId() {
        // 이건 무조건 loan나와서 의미 없을듯염
        return 0;
    }
    public void add(ProductVO productVO) {
        productMapper.insert_LoanManagement(productVO);
    }
    public void update(ProductVO productVO) {
        productMapper.update_LoanManagement(productVO);
    }
    public void delete(int id) {
        productMapper.delete_LoanManagement(id);
    }
}
