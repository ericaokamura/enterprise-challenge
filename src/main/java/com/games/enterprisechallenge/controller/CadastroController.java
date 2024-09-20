package com.games.enterprisechallenge.controller;


import com.games.enterprisechallenge.model.dto.AlunoDTO;
import com.games.enterprisechallenge.model.dto.ContatoDTO;
import com.games.enterprisechallenge.model.dto.OficinaDTO;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;
import com.games.enterprisechallenge.service.CadastroService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @PostMapping("alunos")
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO aluno) {
        return ResponseEntity.ok(cadastroService.cadastrarAluno(aluno));
    }

    @GetMapping("alunos")
    public ResponseEntity<List<AlunoDTO>> retornarAlunos() {
        return ResponseEntity.ok(cadastroService.retornarAlunos());
    }

    @GetMapping("alunos/{aluno_email}")
    public ResponseEntity<AlunoDTO> retornarAluno(@PathVariable("aluno_email") String alunoEmail) {
        return ResponseEntity.ok(cadastroService.retornarAluno(alunoEmail));
    }

    @PutMapping("alunos/{aluno_email}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable("aluno_email") String alunoEmail, @RequestBody AlunoDTO aluno) {
        return ResponseEntity.ok(cadastroService.atualizarAluno(alunoEmail, aluno));
    }

    @DeleteMapping("alunos/{aluno_email}")
    public ResponseEntity<Void> deletarAluno(@PathVariable("aluno_email") String alunoEmail) {
        return ResponseEntity.ok(cadastroService.deletarAluno(alunoEmail));
    }

    @PostMapping("voluntarios")
    public ResponseEntity<VoluntarioDTO> cadastrarVoluntario(@RequestBody VoluntarioDTO voluntario) {
        return ResponseEntity.ok(cadastroService.cadastrarVoluntario(voluntario));
    }

    @GetMapping("voluntarios")
    public ResponseEntity<List<VoluntarioDTO>> retornarVoluntarios() {
        return ResponseEntity.ok(cadastroService.retornarVoluntarios());
    }
    @GetMapping("voluntarios/{voluntario_email}")
    public ResponseEntity<VoluntarioDTO> retornarVoluntario(@PathVariable("voluntario_email") String voluntarioEmail) {
        return ResponseEntity.ok(cadastroService.retornarVoluntario(voluntarioEmail));
    }

    @PutMapping("voluntarios/{voluntario_email}")
    public ResponseEntity<VoluntarioDTO> atualizarVoluntario(@PathVariable("voluntario_email") String voluntarioEmail, @RequestBody VoluntarioDTO voluntarioDTO) {
        return ResponseEntity.ok(cadastroService.atualizarVoluntario(voluntarioEmail, voluntarioDTO));
    }

    @DeleteMapping("voluntarios/{voluntario_email}")
    public ResponseEntity<Void> deletarVoluntario(@PathVariable("voluntario_email") String voluntarioEmail) {
        return ResponseEntity.ok(cadastroService.deletarVoluntario(voluntarioEmail));
    }

    @PostMapping("contatos")
    public ResponseEntity<ContatoDTO> cadastrarContato(@RequestBody ContatoDTO contato) {
        return ResponseEntity.ok(cadastroService.cadastrarContato(contato));
    }

    @GetMapping("contatos")
    public ResponseEntity<List<ContatoDTO>> retornarContatos() {
        return ResponseEntity.ok(cadastroService.retornarContatos());
    }

    @GetMapping("contatos/{contato_email}")
    public ResponseEntity<ContatoDTO> retornarContato(@PathVariable("contato_email") String contatoEmail) {
        return ResponseEntity.ok(cadastroService.retornarContato(contatoEmail));
    }

    @PutMapping("contatos/{contato_email}")
    public ResponseEntity<ContatoDTO> atualizarContato(@PathVariable("contato_email") String contatoEmail, @RequestBody ContatoDTO contatoDTO) {
        return ResponseEntity.ok(cadastroService.atualizarContato(contatoEmail, contatoDTO));
    }

    @DeleteMapping("contatos/{contato_email}")
    public ResponseEntity<Void> deletarContato(@PathVariable("contato_email") String contatoEmail) {
        return ResponseEntity.ok(cadastroService.deletarContato(contatoEmail));
    }

    @GetMapping("oficinas-por-usuario/{usuario_email}")
    public ResponseEntity<OficinaDTO> retornarOficinasUsuario(@PathVariable("usuario_email") String usuarioEmail, @Param("role") String role) {
        return ResponseEntity.ok(cadastroService.retornarOficinasUsuario(usuarioEmail, role));
    }
}
