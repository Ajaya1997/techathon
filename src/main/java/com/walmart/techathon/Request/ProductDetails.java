package com.walmart.techathon.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetails {
    private String productName;
    private long price;
    private String description;
}
