package com.walmart.techathon.repository;

import com.walmart.techathon.entity.User;
import com.walmart.techathon.entity.WplusMember;
import com.walmart.techathon.util.RewardUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Repository
@Slf4j
public class UserRepository {
    private static Set<User> users = new HashSet<>();

    void init() throws ParseException {
        users.add(new User("a0p0id2", "Ajaya", "Panda", "ajaya.panda@walmart.com", "9999999999", new WplusMember("a0p0id2", 5000, RewardUtility.getEpochTime("5/5/2023"), RewardUtility.getEpochTime("5/5/2024"))));
        users.add(new User("r0j02uk", "Rishabh", "Jain", "rishabh.jain3@walmart.com", "9999999999", new WplusMember("r0j02uk", 5000, RewardUtility.getEpochTime("5/5/2023"), RewardUtility.getEpochTime("5/5/2024"))));
        users.add(new User("j0g0i9c", "Jai", "Guwalani", "jaikumar.guwalani@walmart.com", "9999999999", new WplusMember("j0g0i9c", 5000, RewardUtility.getEpochTime("5/5/2023"), RewardUtility.getEpochTime("5/5/2024"))));
        users.add(new User("sudha", "Sudha", "Kumari", "kumari.sudha@walmart.com", "9999999999", new WplusMember("sudha", 5000, RewardUtility.getEpochTime("5/5/2023"), RewardUtility.getEpochTime("5/5/2024"))));
    }

    public User getUserDetails(String userId) {
        log.info("fetching user details for userId {}", userId);
        try {
            init();
            return users.stream().filter(user -> userId.equals(user.getUserId())).findFirst().orElse(new User());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw e;
        }
    }
}
