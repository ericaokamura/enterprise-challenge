package com.games.enterprisechallenge.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO {

    private String nomeCompleto;
    private String numeroCelular;
    private String criticaSugestao;
    private String email;
    private String senha;
    private Long roleId;
    private boolean aceitaTermo;

}
