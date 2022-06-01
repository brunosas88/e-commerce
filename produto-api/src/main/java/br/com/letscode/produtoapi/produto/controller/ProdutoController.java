package br.com.letscode.produtoapi.produto.controller;

import br.com.letscode.produtoapi.gateway.IProdutoController;
import br.com.letscode.produtoapi.produto.dto.RequisicaoProdutoDTO;
import br.com.letscode.produtoapi.produto.dto.RespostaProdutoDTO;
import br.com.letscode.produtoapi.produto.model.Produto;
import br.com.letscode.produtoapi.produto.service.ProdutoService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProdutoController implements IProdutoController {

    private final ProdutoService produtoService;

    @Override
    public RespostaProdutoDTO cadastrarProduto(RequisicaoProdutoDTO requisicaoProdutoDTO) {
        return produtoService.cadastrarProduto(requisicaoProdutoDTO);
    }

    @Override
    public RespostaProdutoDTO buscarProduto(String codigo) {
        return produtoService.buscarProdutoPorCodigo(codigo);
    }

    @Override
    public void updateProduto(RespostaProdutoDTO respostaProdutoDTO) {
        produtoService.updateProduto(respostaProdutoDTO);
    }

    @GetMapping("/produtos")
    public Page<RespostaProdutoDTO> listarProdutos(@QuerydslPredicate(root = Produto.class)  Predicate predicate, Pageable pageable) {
        return produtoService.listarProdutos(predicate, pageable);
    }


}
