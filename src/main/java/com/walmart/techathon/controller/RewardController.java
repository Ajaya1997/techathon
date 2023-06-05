package com.walmart.techathon.controller;

import com.walmart.techathon.Request.ProductDetails;
import com.walmart.techathon.entity.User;
import com.walmart.techathon.response.RewardDetails;
import com.walmart.techathon.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    RewardService rewardService;
    @PostMapping("/getRewardDetails")
    @ResponseBody
    public RewardDetails getRewardDetails(@RequestBody ProductDetails productDetails) {
        return rewardService.getRewardDetails(productDetails);
    }

    @GetMapping("/redeem/{userId}")
    @ResponseBody
    public User redeemRewardCoins(@PathVariable String userId) {
        return rewardService.redeemRewardCoinsAndExtendWPlusMember(userId);
    }
}
