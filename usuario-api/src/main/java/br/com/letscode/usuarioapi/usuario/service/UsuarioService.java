package br.com.letscode.usuarioapi.usuario.service;

import br.com.letscode.usuarioapi.usuario.dto.RequisicaoUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.dto.RespostaUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.model.Usuario;
import br.com.letscode.usuarioapi.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Mono<RespostaUsuarioDTO> cadastrarUsuario(RequisicaoUsuarioDTO requisicaoUsuarioDTO) {
        return usuarioRepository.save(Usuario.convertToModel(requisicaoUsuarioDTO)).map(RespostaUsuarioDTO::convertToDTO);
    }

    public Flux<RespostaUsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().map(RespostaUsuarioDTO::convertToDTO);
    }

    public Mono<RespostaUsuarioDTO> buscarUsuario(String cpf) {
        return usuarioRepository.findUsuarioByCpf(cpf).map(RespostaUsuarioDTO::convertToDTO);
    }
}
