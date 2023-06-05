package com.walmart.techathon.service;

import com.walmart.techathon.Request.ProductDetails;
import com.walmart.techathon.entity.User;
import com.walmart.techathon.response.RewardDetails;

public interface RewardService {
    RewardDetails getRewardDetails(ProductDetails productDetails);

    User redeemRewardCoinsAndExtendWPlusMember(String userId);
}
