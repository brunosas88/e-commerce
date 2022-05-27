package br.com.letscode.usuarioapi.usuario.repository;

import br.com.letscode.usuarioapi.usuario.model.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends ReactiveMongoRepository<Usuario, String> {

    Mono<Usuario> findUsuarioByCpf(String cpf);
}
