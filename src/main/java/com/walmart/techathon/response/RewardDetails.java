package com.walmart.techathon.response;

import com.walmart.techathon.Request.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardDetails {
    private long coins;
    private ProductDetails productDetails;
}
