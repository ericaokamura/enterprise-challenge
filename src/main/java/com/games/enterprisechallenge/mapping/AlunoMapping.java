package com.games.enterprisechallenge.mapping;

import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.dto.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
public class AlunoMapping {

    public static AlunoDTO convertModelToDto(Aluno model) {
        AlunoDTO dto = new AlunoDTO();
        dto.setEmail(model.getEmail());
        dto.setIdade(model.getIdade());
        dto.setNomeCompleto(model.getNomeCompleto());
        dto.setNumeroCelular(model.getNumeroCelular());
        dto.setConheceProgramacao(model.isConheceProgramacao());
        dto.setOficinaId(model.getOficina().getId());
        dto.setRoleId(model.getRole().getId());
        dto.setAceitaTermo(model.isAceitaTermo());
        return dto;
    }

    public static Aluno convertDtoToModel(AlunoDTO dto) {
        Aluno model = new Aluno();
        model.setEmail(dto.getEmail());
        model.setIdade(dto.getIdade());
        model.setNomeCompleto(dto.getNomeCompleto());
        model.setNumeroCelular(dto.getNumeroCelular());
        model.setConheceProgramacao(dto.isConheceProgramacao());
        model.setAceitaTermo(dto.isAceitaTermo());
        return model;
    }
}
