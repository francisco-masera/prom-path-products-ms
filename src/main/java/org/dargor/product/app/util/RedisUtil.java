package org.dargor.product.app.util;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Component
public class RedisUtil<T> {

    private final RedisTemplate<String, T> redisTemplate;

    public void storeValues(final String key, T value, int expire) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }

    public T getValue(final String key) {
        return redisTemplate.opsForList().getOperations().opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }

}
