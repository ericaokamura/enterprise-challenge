package com.games.enterprisechallenge.controller;


import com.games.enterprisechallenge.model.dto.AlunoDTO;
import com.games.enterprisechallenge.model.dto.ContatoDTO;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;
import com.games.enterprisechallenge.service.CadastroService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @RequestMapping("/aluno")
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO aluno) {
        return ResponseEntity.ok(cadastroService.cadastrarAluno(aluno));
    }

    @RequestMapping("/voluntario")
    public ResponseEntity<VoluntarioDTO> cadastrarVoluntario(@RequestBody VoluntarioDTO voluntario) {
        return ResponseEntity.ok(cadastroService.cadastrarVoluntario(voluntario));
    }

    @RequestMapping("/contato")
    public ResponseEntity<ContatoDTO> cadastrarContato(@RequestBody ContatoDTO contato) {
        return ResponseEntity.ok(cadastroService.cadastrarContato(contato));
    }
}
