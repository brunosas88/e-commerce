package br.com.letscode.comprasvalidator.usuario.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequisicaoUsuarioDTO {
    private String cpf;
    private String nome;
    private String email;
    private Integer idade;
    private Endereco endereco;
}
