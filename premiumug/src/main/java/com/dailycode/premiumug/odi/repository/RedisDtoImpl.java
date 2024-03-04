package com.dailycode.premiumug.odi.repository;

import com.dailycode.premiumug.odi.model.RedisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisDtoImpl implements RedisDto{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "PREMIUMS";

    @Override
    public boolean savePremium(RedisModel redisModel) {
        try {
            redisTemplate.opsForHash().put(KEY,redisModel.getId(),redisModel);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RedisModel> getPremiums() {
        List<RedisModel> redisModels;
        redisModels = redisTemplate.opsForHash().values(KEY);
        return redisModels;
    }
}
