package br.com.letscode.comprasvalidator.produto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RespostaProdutoDTO {
    private String codigo;
    private String nome;
    private Float preco;
    private Integer qtdDisponivel;
}
