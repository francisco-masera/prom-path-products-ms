package org.dargor.product.app.util;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Component
public class RedisUtil<T> {

    private static final Gson GSON = new Gson();
    private final RedisTemplate<String, String> redisTemplate;

    public void storeValues(final String key, T value, int expire) {
        redisTemplate.opsForValue().set(key, GSON.toJson(value));
        redisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }

    public Object getValue(final String key) {
        return GSON.fromJson(redisTemplate.opsForValue().get(key), Object.class);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }

}
