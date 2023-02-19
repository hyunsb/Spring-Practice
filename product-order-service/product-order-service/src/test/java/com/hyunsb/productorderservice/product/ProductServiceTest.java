package com.hyunsb.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void 상품등록() {
        final String name = "상품명";
        final Long price = 1000L;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;

        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        productService.addProduct(request);
    }

    private class ProductService {

        public void addProduct(AddProductRequest request) {
            throw new UnsupportedOperationException("Unsupported addProduct");
        }
    }


    private record AddProductRequest(String name, Long price, DiscountPolicy discountPolicy) {

        public AddProductRequest{

            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");

        }
    }

    private enum DiscountPolicy {
        NONE
    }

}
