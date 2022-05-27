package br.com.letscode.usuarioapi.usuario.controller;

import br.com.letscode.usuarioapi.usuario.dto.RequisicaoUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.dto.RespostaUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Mono<RespostaUsuarioDTO>> cadastrarUsuario(@RequestBody RequisicaoUsuarioDTO requisicaoUsuarioDTO) {
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(requisicaoUsuarioDTO));
    }

    @GetMapping
    public ResponseEntity<Flux<RespostaUsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/busca/{cpf}")
    public ResponseEntity<Mono<RespostaUsuarioDTO>> buscarUsuario(@PathVariable String cpf) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(cpf));

    }
}
