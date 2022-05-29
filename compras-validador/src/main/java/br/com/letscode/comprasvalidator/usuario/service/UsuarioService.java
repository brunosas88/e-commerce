package br.com.letscode.comprasvalidator.usuario.service;

import br.com.letscode.comprasvalidator.produto.dto.RespostaProdutoDTO;
import br.com.letscode.comprasvalidator.usuario.dto.RespostaUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    @Value("${webclient.url.usuario}")
    private String WEBCLIENT_URL_USUARIO;

    public RespostaUsuarioDTO buscarUsuario(String cpf) {
        WebClient webClient = WebClient.create(WEBCLIENT_URL_USUARIO);
        return webClient
                .get()
                .uri("/usuario/busca/{cpf}", cpf)
                .retrieve()
                .bodyToMono(RespostaUsuarioDTO.class)
                .block();
    }
}
