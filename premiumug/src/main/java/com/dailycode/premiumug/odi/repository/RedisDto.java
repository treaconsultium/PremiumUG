package com.dailycode.premiumug.odi.repository;



import com.dailycode.premiumug.odi.model.RedisModel;

import java.util.List;

public interface RedisDto {
    boolean savePremium(RedisModel redisModel);

    List<RedisModel> getPremiums();
}
