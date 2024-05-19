package com.games.enterprisechallenge.mapper;

import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.model.dto.UsuarioDTO;

public class UsuarioMapper {

    public static Usuario convertDtoToModel(UsuarioDTO dto) {
        Usuario model = new Usuario();
        model.setNomeUsuario(dto.getNomeUsuario());
        model.setSenha(dto.getSenha());
        return model;
    }

    public static UsuarioDTO convertModelToDto(Usuario model) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNomeUsuario(model.getNomeUsuario());
        dto.setSenha(model.getSenha());
        return dto;
    }
}
