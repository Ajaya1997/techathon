package com.walmart.techathon.service.impl;

import com.walmart.techathon.Request.ProductDetails;
import com.walmart.techathon.entity.User;
import com.walmart.techathon.repository.UserRepository;
import com.walmart.techathon.response.RewardDetails;
import com.walmart.techathon.service.RewardService;
import com.walmart.techathon.util.RewardUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RewardServiceImpl implements RewardService {
    static final int rewardPercent = 2;
    static final long minRewardCoinRequiredForWPlus = 1000;
    @Autowired
    UserRepository userRepository;
    @Override
    public RewardDetails getRewardDetails(ProductDetails productDetails) {
        long price = productDetails.getPrice();
        long rewardCoins = (price/rewardPercent) * 100;
        return new RewardDetails(rewardCoins, productDetails);
    }

    @Override
    public User redeemRewardCoinsAndExtendWPlusMember(String userId) {
        log.info("redeem request received for userId {}", userId);
        try {
            User user = userRepository.getUserDetails(userId);
            long rewardCoins = user.getWplusMember().getRewardCoins();
            if (rewardCoins >= minRewardCoinRequiredForWPlus) {
                user.getWplusMember().setRewardCoins(rewardCoins - minRewardCoinRequiredForWPlus);
                user.getWplusMember().setMemberTill(RewardUtility.addTime(user.getWplusMember().getMemberTill(), 365));
            } else {
                throw new RuntimeException("Don't have sufficient coins to redeem");
            }
            return user;
        } catch (Exception e) {
            throw e;
        }
    }
}
