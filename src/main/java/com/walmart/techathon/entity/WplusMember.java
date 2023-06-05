package com.walmart.techathon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WplusMember {
    private String userId;
    private long rewardCoins;
    private long memberFrom;
    private long memberTill;
}
