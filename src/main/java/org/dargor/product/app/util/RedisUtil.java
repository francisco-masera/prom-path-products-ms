package org.dargor.product.app.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.product.core.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, List<Product>> redisTemplate;

    public void storeValues(final String key, List<Product> value, int expire) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }

    public List<Product> getValues(final String key) throws ClassNotFoundException {
        var values = Optional.ofNullable(redisTemplate.opsForValue().get(key));
        log.info(String.format("Redis values %s", values));
        return values.orElse(Collections.emptyList());
    }

}
