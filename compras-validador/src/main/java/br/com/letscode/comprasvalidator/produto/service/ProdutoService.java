package br.com.letscode.comprasvalidator.produto.service;


import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.gateway.IProdutoController;
import br.com.letscode.comprasvalidator.producer.service.ProducerService;
import br.com.letscode.comprasvalidator.produto.dto.RespostaProdutoDTO;
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

    public RespostaProdutoDTO buscarProduto(String codigo) {
        return iProdutoController.buscarProduto(codigo);
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
            if(Objects.equals(produto.getCodigo(), "null")) {
                Compra compraCancelada = validacaoCompraDTO.getCompra();
                compraCancelada.setValorTotal(0F);
                compraCancelada.setStatus("CANCELADA: Produto de codigo [" + pedido.getCodigoProduto() + "] inexistente");
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
        });

        if (!status.contains(false)){
            produtos.forEach((s, respostaProdutoDTO) -> updateProduto(respostaProdutoDTO));
        }

        return !(status.contains(false));
    }

}
