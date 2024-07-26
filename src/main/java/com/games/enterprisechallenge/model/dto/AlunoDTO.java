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
    private String email;
    private String numeroIdentificacao;
    private Long idade;
    private boolean conheceProgramacao;
}
