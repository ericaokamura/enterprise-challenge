package com.games.enterprisechallenge.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String email;
    private String senha;
    private Long roleId;
    private boolean aceitaTermo;
}
