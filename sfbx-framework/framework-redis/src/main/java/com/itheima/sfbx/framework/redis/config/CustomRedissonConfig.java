package com.itheima.sfbx.framework.redis.config;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(RedissonClient.class)
public class CustomRedissonConfig {
    private final RedisProperties redisProperties;
    @Bean(
            destroyMethod = "shutdown"
    )
    @Primary
    public RedissonClient redisson() {
        Config config = new Config();
        String prefix = "redis://";
        ((SingleServerConfig)config.useSingleServer()
                .setAddress(prefix + this.redisProperties.getHost() + ":" + this.redisProperties.getPort()))
                .setDatabase(this.redisProperties.getDatabase())
                .setPassword(this.redisProperties.getPassword());
        config.setCodec(JsonJacksonCodec.INSTANCE);
        return Redisson.create(config);
    }
}
