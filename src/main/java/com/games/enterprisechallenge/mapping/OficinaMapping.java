package com.games.enterprisechallenge.mapping;

import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.Oficina;
import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.model.dto.AlunoDTO;
import com.games.enterprisechallenge.model.dto.OficinaDTO;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OficinaMapping {

    public static OficinaDTO convertModelToDto(Oficina model) {
        OficinaDTO dto = new OficinaDTO();
        dto.setNomeOficina(model.getNomeOficina());
        dto.setHorarios(model.getHorarios());
        dto.setId(model.getId());
        return dto;
    }

    public static Oficina convertDtoToModel(OficinaDTO dto) {
        Oficina model = new Oficina();
        model.setNomeOficina(dto.getNomeOficina());
        model.setHorarios(dto.getHorarios());
        return model;
    }
}
