package com.example.cards.service.abstraction;

import com.example.cards.model.CacheData;

public interface CacheService {
    void saveCache(CacheData cacheData);

    CacheData getBucket(Long id);


}
