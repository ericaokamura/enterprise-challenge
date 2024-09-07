package com.games.enterprisechallenge.mapping;

import com.games.enterprisechallenge.model.Role;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.model.dto.RoleDTO;
import com.games.enterprisechallenge.model.dto.UsuarioDTO;

public class RoleMapping {

    public static Role convertDtoToModel(RoleDTO dto) {
        Role model = new Role();
        model.setNome(dto.getNome());
        model.setAuthorities(dto.getAuthorities());
        return model;
    }

    public static RoleDTO convertModelToDto(Role model) {
        RoleDTO dto = new RoleDTO();
        dto.setNome(model.getNome());
        dto.setAuthorities(model.getAuthorities());
        return dto;
    }
}
