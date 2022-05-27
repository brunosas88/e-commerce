package br.com.letscode.produtoapi.config;


import br.com.letscode.produtoapi.produto.dto.RespostaProdutoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@AutoConfigureAfter(RedisAutoConfiguration.class)
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String REDIS_HOST;
    @Value("${spring.redis.port}")
    private Integer REDIS_PORT;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, RespostaProdutoDTO> redisTemplate() {
        RedisTemplate<String, RespostaProdutoDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        RedisSerializer<String> serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);

        return template;
    }
}
