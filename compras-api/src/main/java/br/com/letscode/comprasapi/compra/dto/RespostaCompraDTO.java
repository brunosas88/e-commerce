package br.com.letscode.comprasapi.compra.dto;

import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.compra.model.EnderecoEnvio;
import br.com.letscode.comprasapi.pedido.model.Pedido;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespostaCompraDTO {

    private String idCompra;
    private String dataCompra;
    private Float valorTotal;
    private String status;
    private EnderecoEnvio enderecoEnvio;
    private List<Pedido> pedidos = new ArrayList<>();

    public static RespostaCompraDTO convertToDTO(Compra compra) {
        RespostaCompraDTO dto = new RespostaCompraDTO();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dto.setStatus(compra.getStatus());
        dto.setIdCompra(compra.getIdCompra());
        dto.setDataCompra(compra.getDataCompra().format(formato));
        dto.setEnderecoEnvio(compra.getEnderecoEnvio());
        dto.setValorTotal(compra.getValorTotal());
        dto.setPedidos(compra.getPedidos());
        return dto;
    }
}
