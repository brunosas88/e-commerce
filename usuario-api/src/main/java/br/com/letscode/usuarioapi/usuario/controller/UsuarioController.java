package br.com.letscode.usuarioapi.usuario.controller;

import br.com.letscode.usuarioapi.usuario.dto.RequisicaoUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.dto.RespostaUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RespostaUsuarioDTO> cadastrarUsuario(@RequestBody RequisicaoUsuarioDTO requisicaoUsuarioDTO) {
        return usuarioService.cadastrarUsuario(requisicaoUsuarioDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<RespostaUsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
    @GetMapping("/busca/{cpf}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<RespostaUsuarioDTO> buscarUsuario(@PathVariable String cpf) {
        return usuarioService.buscarUsuario(cpf);
    }
}
