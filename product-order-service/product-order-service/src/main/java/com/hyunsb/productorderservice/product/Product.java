package com.hyunsb.productorderservice.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

// 상품 클래스
@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private DiscountPolicy discountPolicy;

    public Product(final String name, final int price, final DiscountPolicy discountPolicy) {

        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
