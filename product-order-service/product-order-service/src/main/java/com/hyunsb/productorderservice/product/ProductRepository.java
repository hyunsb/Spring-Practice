package com.hyunsb.productorderservice.product;

import java.util.HashMap;
import java.util.Map;

// 상품 레포지토리
class ProductRepository {

    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void save(Product product) {
        product.assignId(++sequence);
        persistence.put(product.getId(), product);
    }
}
