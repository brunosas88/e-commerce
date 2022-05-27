package br.com.letscode.usuarioapi.usuario.dto;

import br.com.letscode.usuarioapi.endereco.model.Endereco;
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
