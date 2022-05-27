package br.com.letscode.usuarioapi.usuario.dto;

import br.com.letscode.usuarioapi.usuario.model.Usuario;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RespostaUsuarioDTO {
    private String cpf;
    private String nome;
    private String email;
    private Integer idade;
    private String endereco;

    public static RespostaUsuarioDTO convertToDTO(Usuario model) {
        return RespostaUsuarioDTO.builder().
                cpf(model.getCpf()).
                nome(model.getNome()).
                idade(model.getIdade()).
                email(model.getEmail()).
                endereco("Rua "+ model.getEndereco().getRua() +
                        ", nº " + model.getEndereco().getNumero() +
                        " - " + model.getEndereco().getBairro() +
                        ", " + model.getEndereco().getCidade() +
                        " - " + model.getEndereco().getEstado() +
                        ", " + model.getEndereco().getCep()
                ).
                build();
    }
}
