package br.com.itaucase.apitranferencia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.model.Transferencia;

@Configuration
public class RedisConfig {
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    /*
	     * Conexão: O template é associado a uma fábrica de conexões, que gerencia a conexão com o Redis.
	     * Serialização de Chaves: As chaves serão tratadas como strings.
		 * Serialização de Valores: Os valores serão convertidos para JSON ao serem armazenados no Redis
		 * e convertidos de JSON para objetos Java ao serem recuperados.
	     */
	    template.setConnectionFactory(connectionFactory);
	    template.setKeySerializer(new StringRedisSerializer());
	    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
	    
	    return template;
	}
}