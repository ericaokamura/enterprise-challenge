package com.games.enterprisechallenge.mapping;

import com.games.enterprisechallenge.model.Contato;
import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.model.dto.ContatoDTO;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;

public class ContatoMapping {


    public static ContatoDTO convertModelToDto(Contato model) {
        ContatoDTO dto = new ContatoDTO();
        dto.setEmail(model.getEmail());
        dto.setNomeCompleto(model.getNomeCompleto());
        dto.setNumeroCelular(model.getNumeroCelular());
        dto.setCriticaSugestao(model.getCriticaSugestao());
        dto.setAceitaTermo(model.isAceitaTermo());
        return dto;
    }

    public static Contato convertDtoToModel(ContatoDTO dto) {
        Contato model = new Contato();
        model.setEmail(dto.getEmail());
        model.setNomeCompleto(dto.getNomeCompleto());
        model.setNumeroCelular(dto.getNumeroCelular());
        model.setCriticaSugestao(dto.getCriticaSugestao());
        model.setAceitaTermo(dto.isAceitaTermo());
        return model;
    }
}
