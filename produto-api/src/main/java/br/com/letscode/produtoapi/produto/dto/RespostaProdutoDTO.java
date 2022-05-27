package br.com.letscode.produtoapi.produto.dto;


import br.com.letscode.produtoapi.produto.model.Produto;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("RespostaProdutoDTO")
public class RespostaProdutoDTO implements Serializable {
    private String codigo;
    private String nome;
    private Float preco;
    private Integer qtdDisponivel;

    public static RespostaProdutoDTO convertToDTO(Produto produto) {
        return new RespostaProdutoDTO(produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQtdDisponivel());
    }

}
