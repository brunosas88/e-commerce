package br.com.letscode.comprasvalidator.produto.service;


import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.gateway.IProdutoController;
import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.producer.service.ProducerService;
import br.com.letscode.comprasvalidator.produto.dto.RespostaProdutoDTO;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final IProdutoController iProdutoController;

    private final ProducerService producerService;

    public static Map<String, RespostaProdutoDTO> produtos = new HashMap<>();

    @Retry(name = "busca-bd-error", fallbackMethod = "fallbackBuscarProduto")
    public RespostaProdutoDTO buscarProduto(String codigo) {
        return iProdutoController.buscarProduto(codigo);
    }

    public RespostaProdutoDTO fallbackBuscarProduto(String codigo, Exception e) {
        log.error("ACESSO AO BANCO DE PRODUTOS FALHOU" + "\nERROR: " + e.getMessage());
        return RespostaProdutoDTO.builder().codigo("null").qtdDisponivel(0).build();
    }

    public void updateProduto(RespostaProdutoDTO respostaProdutoDTO) {
        iProdutoController.updateProduto(respostaProdutoDTO);
    }

    public boolean controleProduto(ValidacaoCompraDTO validacaoCompraDTO) {
        List<Boolean> status = new ArrayList<>();
        validacaoCompraDTO.getRequisicaoCompraDTO().getPedido().forEach( pedido -> {
            RespostaProdutoDTO produto = buscarProduto(pedido.getCodigoProduto());
            int quantidadeFinalEstoque = produto.getQtdDisponivel() - pedido.getQtdProduto();
            produto.setQtdDisponivel(quantidadeFinalEstoque);
            validacaoProduto(validacaoCompraDTO, status, pedido, produto, quantidadeFinalEstoque);
        });
        if (!status.contains(false)){
            produtos.forEach((s, respostaProdutoDTO) -> updateProduto(respostaProdutoDTO));
        }
        return !(status.contains(false));
    }

    private void validacaoProduto(ValidacaoCompraDTO validacaoCompraDTO, List<Boolean> status, RequisicaoPedidoDTO pedido, RespostaProdutoDTO produto, int quantidadeFinalEstoque) {
        if(Objects.equals(produto.getCodigo(), "null")) {
            Compra compraCancelada = validacaoCompraDTO.getCompra();
            compraCancelada.setValorTotal(0F);
            compraCancelada.setStatus("CANCELADA: Produto de codigo [" + pedido.getCodigoProduto() + "] não encontrado");
            producerService.enviarMensagem(compraCancelada);
            status.add(false);
        }else if(quantidadeFinalEstoque < 0 ) {
            Compra compraCancelada = validacaoCompraDTO.getCompra();
            compraCancelada.setValorTotal(0F);
            compraCancelada.setStatus("CANCELADA: Estoque do produto de codigo [" + pedido.getCodigoProduto() + "] insuficiente");
            producerService.enviarMensagem(compraCancelada);
            status.add(false);
        }else {
            produtos.put(pedido.getCodigoProduto(), produto);
            status.add(true);
        }
    }

}
