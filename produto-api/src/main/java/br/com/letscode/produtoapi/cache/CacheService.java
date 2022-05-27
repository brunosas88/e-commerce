package br.com.letscode.produtoapi.cache;

import br.com.letscode.produtoapi.produto.dto.RespostaProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, RespostaProdutoDTO> redisTemplate;

    public void salvarEmCache(RespostaProdutoDTO respostaProdutoDTO) {
        redisTemplate.opsForValue().set(respostaProdutoDTO.getCodigo(), respostaProdutoDTO);
    }

    public Optional<RespostaProdutoDTO> busca(String codigo) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(codigo));
    }
}
