package com.example.cards.service.concrete;

import com.example.cards.aspect.Loggable;
import com.example.cards.model.CacheData;
import com.example.cards.service.abstraction.CacheService;
import com.example.cards.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Loggable
public class CacheServiceHandler implements CacheService {
    private final CacheUtil cacheUtil;


    @Override
    public void saveCache(CacheData cacheData) {

        var cacheKey = "ms-cards:card-id:"+cacheData.getId();
        cacheUtil.saveToCache(cacheKey,cacheData,1l,ChronoUnit.MINUTES);
    }

    @Override
    public CacheData getBucket(Long id) {
        var cacheKey = "ms-cards:card-id:"+id;
        return cacheUtil.getBucket(cacheKey);
    }
}
