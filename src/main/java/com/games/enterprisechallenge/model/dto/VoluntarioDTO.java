package com.games.enterprisechallenge.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioDTO {
    private String nomeCompleto;
    private String numeroCelular;
    private String email;
    private String motivacao;

}
