package br.com.letscode.comprasvalidator.pedido.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido {
    private String id;
    private String codigo;
    private String nome;
    private Integer quantidade;
    private Float valor;
}
