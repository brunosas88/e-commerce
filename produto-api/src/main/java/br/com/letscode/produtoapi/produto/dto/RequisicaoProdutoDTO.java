package br.com.letscode.produtoapi.produto.dto;

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
