package com.games.enterprisechallenge.service;

import com.games.enterprisechallenge.mapping.OficinaMapping;
import com.games.enterprisechallenge.model.dto.OficinaDTO;
import com.games.enterprisechallenge.repository.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    public List<OficinaDTO> retornaTodasOficinas() {
        List<OficinaDTO> oficinas = new ArrayList<>();
        oficinaRepository.findAll().forEach(o -> oficinas.add(OficinaMapping.convertModelToDto(o)));
        return oficinas;
    }
}
