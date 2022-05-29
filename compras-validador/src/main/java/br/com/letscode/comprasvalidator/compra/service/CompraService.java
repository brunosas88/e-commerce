package br.com.letscode.comprasvalidator.compra.service;

import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.compra.model.EnderecoEnvio;
import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.pedido.service.PedidoService;
import br.com.letscode.comprasvalidator.producer.service.ProducerService;
import br.com.letscode.comprasvalidator.produto.dto.RespostaProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import br.com.letscode.comprasvalidator.usuario.dto.RespostaUsuarioDTO;
import br.com.letscode.comprasvalidator.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final ProducerService producerService;
    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    public void cadastraCompra(ValidacaoCompraDTO validacaoCompraDTO) {
        Compra novaCompra = validacaoCompraDTO.getCompra();
        novaCompra.setStatus("FINALIZADA");
        novaCompra.setEnderecoEnvio(cadastrarEnderecoEnvio(validacaoCompraDTO.getCompra().getCpfCliente()));
        novaCompra.setValorTotal(calcularValorTotalPedidos(validacaoCompraDTO.getRequisicaoCompraDTO().getPedido()));
        novaCompra.getPedidos().addAll(validacaoCompraDTO.getRequisicaoCompraDTO().getPedido()
                .stream()
                .map(pedidoService::salvarPedido
                )
                .collect(Collectors.toList())
        );

        producerService.enviarMensagem(novaCompra);
    }

    private EnderecoEnvio cadastrarEnderecoEnvio(String cpf) {
        RespostaUsuarioDTO usuario = usuarioService.buscarUsuario(cpf);
        return new EnderecoEnvio(usuario.getNome(), usuario.getEndereco());
    }

    private Float calcularValorTotalPedidos (List<RequisicaoPedidoDTO> pedidosDTO) {
        Float valorTotal = 0F;
        for (RequisicaoPedidoDTO requisicaoPedidoDTO : pedidosDTO) {
            RespostaProdutoDTO produto = ProdutoService.produtos.get(requisicaoPedidoDTO.getCodigoProduto());
            valorTotal = valorTotal + ( produto.getPreco() * requisicaoPedidoDTO.getQtdProduto() );
        }
        return valorTotal;
    }
}
