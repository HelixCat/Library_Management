package com.mahdi.website.service.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    public void evictUserCache(String username) {
        if (Objects.nonNull(cacheManager.getCache("users"))) {
            cacheManager.getCache("users").evict(username);
            log.info("Evicted user cache for: {}", username);
        }
    }

    public void evictUserDetailCache(String email) {
        if (Objects.nonNull(cacheManager.getCache("userDetails"))) {
            cacheManager.getCache("userDetails").evict(email);
            log.info("Evicted user detail cache for: {}", email);
        }
    }

    public void evictAllUserSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("userSearch"))) {
            cacheManager.getCache("userSearch").clear();
            log.info("Evicted all user search cache");
        }
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
            log.info("Evicted all entries from cache: {}", cacheName);
        });
    }
}