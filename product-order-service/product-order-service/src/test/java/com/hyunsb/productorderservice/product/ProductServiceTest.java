package com.hyunsb.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPort productPort;


    @Test
    void 상품수정() {

        // 상품 등록 요청
        productService.addProduct(ProductSteps.상품등록요청_생성());

        UpdateProductRequest request = ProductSteps.상품수정요청_생성();
        long productId = 1L;

        // 상품 수정 요청
        productService.updateProduct(productId, request);

        // 상품 수정 요청에 대한 응답을 검증

        ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
        GetProductResponse productResponse = response.getBody();

        assertThat(productResponse.name()).isEqualTo("상품 수정");
        assertThat(productResponse.price()).isEqualTo(2000);
    }

}
