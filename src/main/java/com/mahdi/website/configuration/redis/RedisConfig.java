package com.mahdi.website.configuration.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${spring.data.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.data.redis.port:6379}")
    private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setUsername("default");
        if (!redisPassword.isEmpty()) {
            config.setPassword(redisPassword);
        }
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configure serializers
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jsonRedisSerializer());
        template.setHashValueSerializer(jsonRedisSerializer());

        template.setDefaultSerializer(jsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer jsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .serializeKeysWith(org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
                        .fromSerializer(jsonRedisSerializer()))
                .disableCachingNullValues();

        // Configure different TTLs for different caches
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // User cache - longer TTL since user data doesn't change frequently
        cacheConfigurations.put("users", defaultCacheConfig.entryTtl(Duration.ofHours(2)));
        // User search cache - shorter TTL since search results may change
        cacheConfigurations.put("userSearch", defaultCacheConfig.entryTtl(Duration.ofMinutes(30)));
        // User details cache - medium TTL
        cacheConfigurations.put("userDetails", defaultCacheConfig.entryTtl(Duration.ofHours(1)));

        // Book caches
        cacheConfigurations.put("books", defaultCacheConfig.entryTtl(Duration.ofHours(2)));
        cacheConfigurations.put("bookSearch", defaultCacheConfig.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("bookDetails", defaultCacheConfig.entryTtl(Duration.ofHours(1)));

        // Address caches
        cacheConfigurations.put("addresses", defaultCacheConfig.entryTtl(Duration.ofHours(2)));
        cacheConfigurations.put("addressSearch", defaultCacheConfig.entryTtl(Duration.ofMinutes(30)));

        // Author caches
        cacheConfigurations.put("authors", defaultCacheConfig.entryTtl(Duration.ofHours(2)));
        cacheConfigurations.put("authorSearch", defaultCacheConfig.entryTtl(Duration.ofMinutes(30)));

        // Publisher caches
        cacheConfigurations.put("publishers", defaultCacheConfig.entryTtl(Duration.ofHours(2)));
        cacheConfigurations.put("publisherSearch", defaultCacheConfig.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("publisherDetails", defaultCacheConfig.entryTtl(Duration.ofHours(1)));

        // Translator caches
        cacheConfigurations.put("translators", defaultCacheConfig.entryTtl(Duration.ofHours(2)));
        cacheConfigurations.put("translatorSearch", defaultCacheConfig.entryTtl(Duration.ofMinutes(30)));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}