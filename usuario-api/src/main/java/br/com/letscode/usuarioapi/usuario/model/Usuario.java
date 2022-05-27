package br.com.letscode.usuarioapi.usuario.model;

import br.com.letscode.usuarioapi.endereco.model.Endereco;
import br.com.letscode.usuarioapi.usuario.dto.RequisicaoUsuarioDTO;
import br.com.letscode.usuarioapi.usuario.dto.RespostaUsuarioDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Usuario {
    @Id
    private String id;

    @Indexed(unique = true)
    private String cpf;

    private String nome;

    @Indexed(unique = true)
    private String email;

    private Integer idade;

    private Endereco endereco;

    public static Usuario convertToModel(RequisicaoUsuarioDTO dto) {
        return Usuario.builder().
                id(UUID.randomUUID().toString()).
                cpf(dto.getCpf()).
                nome(dto.getNome()).
                email(dto.getEmail()).
                idade(dto.getIdade()).
                endereco(dto.getEndereco()).
                build();
    }
}
