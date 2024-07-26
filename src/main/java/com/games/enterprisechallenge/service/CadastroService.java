package com.games.enterprisechallenge.service;

import com.games.enterprisechallenge.mapping.AlunoMapping;
import com.games.enterprisechallenge.mapping.ContatoMapping;
import com.games.enterprisechallenge.mapping.VoluntarioMapping;
import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.Contato;
import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.model.dto.AlunoDTO;
import com.games.enterprisechallenge.model.dto.ContatoDTO;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;
import com.games.enterprisechallenge.repository.AlunoRepository;
import com.games.enterprisechallenge.repository.ContatoRepository;
import com.games.enterprisechallenge.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public AlunoDTO cadastrarAluno(AlunoDTO dto) {
        Aluno aluno = alunoRepository.save(AlunoMapping.convertDtoToModel(dto));
        return AlunoMapping.convertModelToDto(aluno);
    }

    public ContatoDTO cadastrarContato(ContatoDTO dto) {
        Contato contato = contatoRepository.save(ContatoMapping.convertDtoToModel(dto));
        return ContatoMapping.convertModelToDto(contato);
    }

    public VoluntarioDTO cadastrarVoluntario(VoluntarioDTO dto) {
        Voluntario voluntario = voluntarioRepository.save(VoluntarioMapping.convertDtoToModel(dto));
        return VoluntarioMapping.convertModelToDto(voluntario);
    }
}
