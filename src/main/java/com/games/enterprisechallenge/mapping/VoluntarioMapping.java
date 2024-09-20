package com.games.enterprisechallenge.mapping;

import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;

public class VoluntarioMapping {

    public static VoluntarioDTO convertModelToDto(Voluntario model) {
        VoluntarioDTO dto = new VoluntarioDTO();
        dto.setEmail(model.getEmail());
        dto.setNomeCompleto(model.getNomeCompleto());
        dto.setNumeroCelular(model.getNumeroCelular());
        dto.setMotivacao(model.getMotivacao());
        dto.setRoleId(model.getRole().getId());
        dto.setOficinaId(model.getOficina().getId());
        dto.setAceitaTermo(model.isAceitaTermo());
        return dto;
    }

    public static Voluntario convertDtoToModel(VoluntarioDTO dto) {
        Voluntario model = new Voluntario();
        model.setEmail(dto.getEmail());
        model.setNomeCompleto(dto.getNomeCompleto());
        model.setNumeroCelular(dto.getNumeroCelular());
        model.setMotivacao(dto.getMotivacao());
        model.setAceitaTermo(dto.isAceitaTermo());
        return model;
    }
}
