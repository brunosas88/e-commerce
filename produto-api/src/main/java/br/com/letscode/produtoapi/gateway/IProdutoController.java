package br.com.letscode.produtoapi.gateway;

import br.com.letscode.produtoapi.produto.dto.RequisicaoProdutoDTO;
import br.com.letscode.produtoapi.produto.dto.RespostaProdutoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface IProdutoController {

    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    RespostaProdutoDTO cadastrarProduto(@RequestBody RequisicaoProdutoDTO parametro);

    @GetMapping("/produtos/busca/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    RespostaProdutoDTO buscarProduto(@PathVariable String codigo);

    @PutMapping("/produtos/update")
    @ResponseStatus(HttpStatus.OK)
    void updateProduto(@RequestBody RespostaProdutoDTO respostaProdutoDTO);
}
