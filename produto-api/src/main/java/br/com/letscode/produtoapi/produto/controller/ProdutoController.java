package br.com.letscode.produtoapi.produto.controller;


import br.com.letscode.produtoapi.produto.dto.RequisicaoProdutoDTO;
import br.com.letscode.produtoapi.produto.dto.RespostaProdutoDTO;
import br.com.letscode.produtoapi.produto.model.Produto;
import br.com.letscode.produtoapi.produto.service.ProdutoService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<RespostaProdutoDTO> cadastrarProduto(@RequestBody RequisicaoProdutoDTO requisicaoProdutoDTO) {
        return ResponseEntity.ok(produtoService.cadastrarProduto(requisicaoProdutoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<RespostaProdutoDTO>> listarProdutos(@QuerydslPredicate(root = Produto.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarProdutos(predicate, pageable));
    }

    @GetMapping("/busca/{codigo}")
    public ResponseEntity<RespostaProdutoDTO> buscarProduto(@PathVariable String codigo) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorCodigo(codigo));
    }

    @PatchMapping("/update")
    public void updateProduto(@RequestBody RespostaProdutoDTO respostaProdutoDTO) {
        produtoService.updateProduto(respostaProdutoDTO);
    }
}
