package br.com.letscode.comprasvalidator.produto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequisicaoProdutoDTO {
    private String nome;
    private Float preco;
    private Integer qtdDisponivel;
}
