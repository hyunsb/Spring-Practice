package com.hyunsb.productorderservice.product;

import org.springframework.util.Assert;

// 상품 등록 리퀘스트
record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {

    public AddProductRequest {

        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");

    }
}
