package com.example.web3study.conf;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import javax.annotation.Resource;

@Configuration
@EnableCaching //缓存启动注解
public class RedisConfig {



    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
     * 配置自定义redisTemplate
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);

        //使用FastJsonRedisSerializer序列化和反序列化redis的key、value值
        template.setValueSerializer(fastJsonRedisSerializer());
        template.setKeySerializer(fastJsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * fastjson序列化Bean
     * @return
     */
    @Bean
    public FastJsonRedisSerializer<?> fastJsonRedisSerializer() {
        return new FastJsonRedisSerializer<>(Object.class);
    }

}
