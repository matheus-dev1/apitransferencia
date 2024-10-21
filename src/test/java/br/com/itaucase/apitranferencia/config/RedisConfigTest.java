package br.com.itaucase.apitranferencia.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfigTest {

    @Mock
    private RedisConnectionFactory redisConnectionFactory;

    @InjectMocks
    private RedisConfig redisConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRedisTemplateConfiguracao() {
        RedisTemplate<String, Object> redisTemplate = redisConfig.redisTemplate(redisConnectionFactory);

        assertNotNull(redisTemplate.getConnectionFactory(), "A RedisConnectionFactory não deve ser nula");
        assertEquals(redisConnectionFactory, redisTemplate.getConnectionFactory(), 
                "A RedisConnectionFactory configurada está incorreta");
        assertTrue(redisTemplate.getKeySerializer() instanceof StringRedisSerializer, 
                "O serializador da chave deve ser StringRedisSerializer");
        assertTrue(redisTemplate.getValueSerializer() instanceof Jackson2JsonRedisSerializer, 
                "O serializador do valor deve ser Jackson2JsonRedisSerializer");
    }
}