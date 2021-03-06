package br.com.letscode.comprasvalidator.consumidor.service;

import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.service.CompraService;
import br.com.letscode.comprasvalidator.metricas.Metricas;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumidorService {

    private final CompraService compraService;
    private final ProdutoService produtoService;
    private final Metricas metricas;

    @CircuitBreaker(name="kafka-consumidor-error")
    @KafkaListener(topics = "COMPRA_PROCESSADA", groupId = "grupo-1")
    public void receberMensagem(ValidacaoCompraDTO validacaoCompraDTO) {
        metricas.incrementarContadorCP();
        if (produtoService.controleProduto(validacaoCompraDTO)){
            metricas.incrementarContadorCV();
            compraService.cadastraCompra(validacaoCompraDTO);
        }
    }
}
