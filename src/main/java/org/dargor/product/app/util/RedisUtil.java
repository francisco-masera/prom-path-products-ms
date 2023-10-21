package org.dargor.product.app.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class RedisUtil<T> {

    private static final Gson GSON = new GsonBuilder().disableInnerClassSerialization().create();
    private final RedisTemplate<String, String> redisTemplate;

    public void storeValues(final String key, T value, int expire) {
        redisTemplate.opsForValue().set(key, GSON.toJson(value));
        redisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }

    public Object getValues(final String key, String className) throws ClassNotFoundException {
        var values = redisTemplate.opsForValue().get(key);
        log.info(String.format("Redis values %s", values));
        return ObjectUtils.isEmpty(values) ? Optional.empty() : GSON.fromJson(values, Class.forName(className));
    }

}
