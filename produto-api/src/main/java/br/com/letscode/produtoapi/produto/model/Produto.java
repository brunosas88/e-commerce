package br.com.letscode.produtoapi.produto.model;


import br.com.letscode.produtoapi.produto.dto.RequisicaoProdutoDTO;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", unique = true)
    private String codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco", columnDefinition = "FLOAT CHECK (preco > 0)")
    private Float preco;

    @Column(name = "qtd_disponivel", columnDefinition = "INT CHECK (qtd_disponivel >= 0)")
    private Integer qtdDisponivel;

    public static Produto convertToModel(RequisicaoProdutoDTO dto) {
        return Produto.builder()
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .qtdDisponivel(dto.getQtdDisponivel())
                .build();
    }

    public static String gerarCodigo() {
        return RandomStringUtils.random(13, false, true);
    }
}
