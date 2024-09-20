package com.games.enterprisechallenge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {

    private String nomeCompleto;
    private String numeroCelular;
    private Long idade;
    private boolean conheceProgramacao;
    private Long oficinaId;
    private String email;
    private String senha;
    private Long roleId;
    private boolean aceitaTermo;

}
