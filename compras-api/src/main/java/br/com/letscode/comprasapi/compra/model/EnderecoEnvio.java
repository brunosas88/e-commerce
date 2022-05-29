package br.com.letscode.comprasapi.compra.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnderecoEnvio {
    private String nome;
    private String endereco;
}
