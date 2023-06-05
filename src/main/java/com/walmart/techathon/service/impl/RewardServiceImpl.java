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

import java.text.ParseException;

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
        long rewardCoins = (price*rewardPercent) / 100;
        return new RewardDetails(rewardCoins, productDetails);
    }

    @Override
    public User redeemRewardCoinsAndExtendWPlusMember(String userId) {
        log.info("redeem request received for userId {}", userId);
        // storing the cloning version because we are not using DB for now. Otherwise, we could have used the @transactional
        User clone = null;
        User user = null;
        try {
            user = userRepository.getUserDetails(userId);
            clone = (User) user.clone();
            long rewardCoins = user.getWplusMember().getRewardCoins();
            if (rewardCoins >= minRewardCoinRequiredForWPlus) {
                user.getWplusMember().setRewardCoins(rewardCoins - minRewardCoinRequiredForWPlus);
                user.getWplusMember().setMemberTill(RewardUtility.addTime(user.getWplusMember().getMemberTill(), 365));
            } else {
                throw new RuntimeException("Don't have sufficient coins to redeem");
            }
            return user;
        } catch (Exception e) {
            log.error("failed to redeem the coins ", e);
            // if anything goes wrong while reward redeem, rolling back.
            if (clone != null)
                user.setWplusMember(clone.getWplusMember());
            throw new RuntimeException("failed to redeem the coin");
        }
    }
}
