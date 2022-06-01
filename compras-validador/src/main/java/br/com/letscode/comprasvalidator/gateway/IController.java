package br.com.letscode.comprasvalidator.gateway;

import br.com.letscode.comprasvalidator.usuario.dto.RequisicaoUsuarioDTO;
import br.com.letscode.comprasvalidator.usuario.dto.RespostaUsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("usuario-api")
public interface IController {

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    RespostaUsuarioDTO cadastrar(@RequestBody RequisicaoUsuarioDTO parametro);

    @GetMapping("/usuario")
    @ResponseStatus(HttpStatus.FOUND)
    List<RespostaUsuarioDTO> listar();

    @GetMapping("/usuario/busca/{cpf}")
    @ResponseStatus(HttpStatus.FOUND)
    RespostaUsuarioDTO buscar(@PathVariable String cpf);
}
