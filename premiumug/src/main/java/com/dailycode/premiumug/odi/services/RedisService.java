package com.dailycode.premiumug.odi.services;

import com.dailycode.premiumug.odi.model.RedisModel;

import java.util.List;

public interface RedisService {
    boolean savePremiumsInRedis(RedisModel redisModel);

    List<RedisModel> getPremiums();
}
