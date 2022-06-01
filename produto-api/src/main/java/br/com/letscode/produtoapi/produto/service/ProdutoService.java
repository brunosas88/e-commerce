package br.com.letscode.produtoapi.produto.service;

import br.com.letscode.produtoapi.cache.CacheService;
import br.com.letscode.produtoapi.produto.dto.RequisicaoProdutoDTO;
import br.com.letscode.produtoapi.produto.dto.RespostaProdutoDTO;
import br.com.letscode.produtoapi.produto.model.Produto;
import br.com.letscode.produtoapi.produto.repository.ProdutoRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CacheService cacheService;

    public RespostaProdutoDTO cadastrarProduto (RequisicaoProdutoDTO requisicaoProdutoDTO){
        Produto novoProduto = Produto.convertToModel(requisicaoProdutoDTO);
        novoProduto.setCodigo(Produto.gerarCodigo());
        while ( produtoRepository.findProdutoByCodigo(novoProduto.getCodigo()).isPresent() ) {
            novoProduto.setCodigo(Produto.gerarCodigo());
        }
        return RespostaProdutoDTO.convertToDTO(produtoRepository.save(novoProduto));
    }

    public Page<RespostaProdutoDTO> listarProdutos(Predicate predicate, Pageable pageable) {
        return produtoRepository.findAll(predicate, pageable).map(RespostaProdutoDTO::convertToDTO);
    }

    public RespostaProdutoDTO buscarProdutoPorCodigo (String codigo){
        return cacheService.busca(codigo).orElseGet(()-> {
            produtoRepository.findProdutoByCodigo(codigo).ifPresent(produto ->
                    cacheService.salvarEmCache(RespostaProdutoDTO.convertToDTO(produto)));
                    return RespostaProdutoDTO.convertToDTO(produtoRepository.findProdutoByCodigo(codigo).orElseGet(()->
                            Produto.builder().codigo("null").qtdDisponivel(0).build()));
        });
    }

    public void updateProduto(RespostaProdutoDTO respostaProdutoDTO) {
        produtoRepository.findProdutoByCodigo(respostaProdutoDTO.getCodigo()).
                ifPresent(produtoAtualizar -> {
                    produtoAtualizar.setCodigo(respostaProdutoDTO.getCodigo());
                    produtoAtualizar.setPreco(respostaProdutoDTO.getPreco());
                    produtoAtualizar.setQtdDisponivel(respostaProdutoDTO.getQtdDisponivel());
                    produtoRepository.save(produtoAtualizar);
                    cacheService.salvarEmCache(RespostaProdutoDTO.convertToDTO(produtoAtualizar));
                });
    }
}
