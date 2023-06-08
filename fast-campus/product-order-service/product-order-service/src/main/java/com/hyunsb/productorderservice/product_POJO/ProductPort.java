package com.hyunsb.productorderservice.product_POJO;

interface ProductPort {
    void save(final Product product);

    Product getProduct(long productId);
}
