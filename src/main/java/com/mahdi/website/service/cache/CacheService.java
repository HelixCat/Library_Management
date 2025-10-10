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
            Objects.requireNonNull(cacheManager.getCache("users")).evict(username);
            log.info("Evicted user cache for: {}", username);
        }
    }

    public void evictUserDetailCache(String email) {
        if (Objects.nonNull(cacheManager.getCache("userDetails"))) {
            Objects.requireNonNull(cacheManager.getCache("userDetails")).evict(email);
            log.info("Evicted user detail cache for: {}", email);
        }
    }

    public void evictAllUserSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("userSearch"))) {
            Objects.requireNonNull(cacheManager.getCache("userSearch")).clear();
            log.info("Evicted all user search cache");
        }
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
            log.info("Evicted all entries from cache: {}", cacheName);
        });
    }

    public void evictBookCache(Long bookId) {
        if (Objects.nonNull(cacheManager.getCache("books"))) {
            Objects.requireNonNull(cacheManager.getCache("books")).evict(bookId);
            log.info("Evicted book cache for: {}", bookId);
        }
    }

    public void evictBookDetailCache(Long bookId) {
        if (Objects.nonNull(cacheManager.getCache("bookDetails"))) {
            Objects.requireNonNull(cacheManager.getCache("bookDetails")).evict(bookId);
            log.info("Evicted book detail cache for: {}", bookId);
        }
    }

    public void evictAllBookSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("bookSearch"))) {
            Objects.requireNonNull(cacheManager.getCache("bookSearch")).clear();
            log.info("Evicted all book search cache");
        }
    }

    public void evictAddressCache(Long addressId) {
        if (Objects.nonNull(cacheManager.getCache("addresses"))) {
            Objects.requireNonNull(cacheManager.getCache("addresses")).evict(addressId);
            log.info("Evicted address cache for: {}", addressId);
        }
    }

    public void evictAllAddressSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("addressSearch"))) {
            Objects.requireNonNull(cacheManager.getCache("addressSearch")).clear();
            log.info("Evicted all address search cache");
        }
    }

    public void evictAuthorCache(Long authorId) {
        if (Objects.nonNull(cacheManager.getCache("authors"))) {
            Objects.requireNonNull(cacheManager.getCache("authors")).evict(authorId);
            log.info("Evicted author cache for: {}", authorId);
        }
    }

    public void evictAllAuthorSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("authorSearch"))) {
            Objects.requireNonNull(cacheManager.getCache("authorSearch")).clear();
            log.info("Evicted all author search cache");
        }
    }

    public void evictPublisherCache(Long publisherId) {
        if (Objects.nonNull(cacheManager.getCache("publishers"))) {
            Objects.requireNonNull(cacheManager.getCache("publishers")).evict(publisherId);
            log.info("Evicted publisher cache for: {}", publisherId);
        }
    }

    public void evictPublisherDetailCache(Long publisherId) {
        if (Objects.nonNull(cacheManager.getCache("publisherDetails"))) {
            Objects.requireNonNull(cacheManager.getCache("publisherDetails")).evict(publisherId);
            log.info("Evicted publisher detail cache for: {}", publisherId);
        }
    }

    public void evictAllPublisherSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("publisherSearch"))) {
            Objects.requireNonNull(cacheManager.getCache("publisherSearch")).clear();
            log.info("Evicted all publisher search cache");
        }
    }

    public void evictTranslatorCache(Long translatorId) {
        if (Objects.nonNull(cacheManager.getCache("translators"))) {
            Objects.requireNonNull(cacheManager.getCache("translators")).evict(translatorId);
            log.info("Evicted translator cache for: {}", translatorId);
        }
    }

    public void evictAllTranslatorSearchCache() {
        if (Objects.nonNull(cacheManager.getCache("translatorSearch"))) {
            Objects.requireNonNull(cacheManager.getCache("translatorSearch")).clear();
            log.info("Evicted all translator search cache");
        }
    }
}