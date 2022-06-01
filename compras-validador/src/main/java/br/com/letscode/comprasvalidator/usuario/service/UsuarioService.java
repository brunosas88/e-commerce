package br.com.letscode.comprasvalidator.usuario.service;

import br.com.letscode.comprasvalidator.usuario.dto.RespostaUsuarioDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final EurekaClient eurekaClient;

    public RespostaUsuarioDTO buscarUsuario(String cpf) {
        InstanceInfo instance = eurekaClient
                .getApplication("usuario-api")
                .getInstances()
                .get(0);

        String hostName = instance.getHostName();
        int port = instance.getPort();
        String url = "http://" + hostName + ":" + port;

        WebClient webClient = WebClient.create(url);
        return webClient
                .get()
                .uri("/usuario/busca/{cpf}", cpf)
                .retrieve()
                .bodyToMono(RespostaUsuarioDTO.class)
                .block();
    }
}
