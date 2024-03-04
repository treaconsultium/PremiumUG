package com.dailycode.premiumug.odi.services;

import com.dailycode.premiumug.odi.model.RedisModel;
import com.dailycode.premiumug.odi.repository.RedisDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServiceImpl implements RedisService{
    private final RedisDto redisDto;

    public RedisServiceImpl(RedisDto redisDto) {
        this.redisDto = redisDto;
    }

    @Override
    public boolean savePremiumsInRedis(RedisModel redisModel) {
        return redisDto.savePremium(redisModel);
    }

    @Override
    public List<RedisModel> getPremiums() {
        return redisDto.getPremiums();
    }
}
