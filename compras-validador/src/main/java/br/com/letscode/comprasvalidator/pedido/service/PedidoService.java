package br.com.letscode.comprasvalidator.pedido.service;

import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.pedido.model.Pedido;
import br.com.letscode.comprasvalidator.produto.dto.RespostaProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    public Pedido salvarPedido(RequisicaoPedidoDTO pedidoDTO) {
        Pedido novoPedido = new Pedido();
        RespostaProdutoDTO produto = ProdutoService.produtos.get(pedidoDTO.getCodigoProduto());
        novoPedido.setId(UUID.randomUUID().toString());
        novoPedido.setCodigo(pedidoDTO.getCodigoProduto());
        novoPedido.setNome(produto.getNome());
        novoPedido.setQuantidade(pedidoDTO.getQtdProduto());
        novoPedido.setValor(produto.getPreco());
        return novoPedido;
    }
}
