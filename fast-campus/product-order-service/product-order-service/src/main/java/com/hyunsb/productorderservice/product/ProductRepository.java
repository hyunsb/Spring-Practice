package com.hyunsb.productorderservice.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// 상품 레포지토리
@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
}
