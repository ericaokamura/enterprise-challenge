package com.games.enterprisechallenge.service;

import com.games.enterprisechallenge.exception.*;
import com.games.enterprisechallenge.mapping.AlunoMapping;
import com.games.enterprisechallenge.mapping.ContatoMapping;
import com.games.enterprisechallenge.mapping.VoluntarioMapping;
import com.games.enterprisechallenge.model.*;
import com.games.enterprisechallenge.model.dto.AlunoDTO;
import com.games.enterprisechallenge.model.dto.ContatoDTO;
import com.games.enterprisechallenge.model.dto.VoluntarioDTO;
import com.games.enterprisechallenge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AlunoDTO cadastrarAluno(AlunoDTO dto) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmail(dto.getEmail());
        if(alunoOptional.isPresent()) {
            throw new AlunoJaExistenteException("Aluno já existente.");
        }
        Optional<Role> roleOptional = roleRepository.findById(dto.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }
        Optional<Oficina> oficinaOptional = oficinaRepository.findById(dto.getOficinaId());
        if(oficinaOptional.isEmpty()) {
            throw new OficinaNaoExistenteException("Oficina informada não existente.");
        }

        Aluno salvarAluno = AlunoMapping.convertDtoToModel(dto);
        salvarAluno.setSenha(passwordEncoder.encode(dto.getSenha()));
        salvarAluno.setRole(roleOptional.get());
        salvarAluno.setOficina(oficinaOptional.get());
        Aluno aluno = alunoRepository.save(salvarAluno);

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuarioRepository.save(usuario);

        return AlunoMapping.convertModelToDto(aluno);
    }

    public ContatoDTO cadastrarContato(ContatoDTO dto) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(dto.getEmail());
        if(contatoOptional.isPresent()) {
            throw new ContatoJaExistenteException("Contato já existente.");
        }
        Optional<Role> roleOptional = roleRepository.findById(dto.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }
        Contato salvarContato = ContatoMapping.convertDtoToModel(dto);
        salvarContato.setSenha(passwordEncoder.encode(dto.getSenha()));
        salvarContato.setRole(roleOptional.get());
        Contato contato = contatoRepository.save(salvarContato);

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuarioRepository.save(usuario);

        return ContatoMapping.convertModelToDto(contato);
    }

    public VoluntarioDTO cadastrarVoluntario(VoluntarioDTO dto) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findByEmail(dto.getEmail());
        if(voluntarioOptional.isPresent()) {
            throw new VoluntarioJaExistenteException("Voluntário já existente.");
        }
        Optional<Role> roleOptional = roleRepository.findById(dto.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }
        Optional<Oficina> oficinaOptional = oficinaRepository.findById(dto.getOficinaId());
        if(oficinaOptional.isEmpty()) {
            throw new OficinaNaoExistenteException("Oficina informada não existente.");
        }
        Voluntario salvarVoluntario = VoluntarioMapping.convertDtoToModel(dto);
        salvarVoluntario.setSenha(passwordEncoder.encode(dto.getSenha()));
        salvarVoluntario.setRole(roleOptional.get());
        salvarVoluntario.setOficina(oficinaOptional.get());
        Voluntario voluntario = voluntarioRepository.save(salvarVoluntario);

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuarioRepository.save(usuario);

        return VoluntarioMapping.convertModelToDto(voluntario);
    }

    public List<AlunoDTO> retornarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoDTO> retorno = new ArrayList<>();
        alunos.forEach(a -> retorno.add(AlunoMapping.convertModelToDto(a)));
        return retorno;
    }

    public AlunoDTO retornarAluno(String alunoEmail) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmail(alunoEmail);
        if(alunoOptional.isEmpty()) {
            throw new AlunoNaoExisteException("Aluno não existe no nosso cadastro.");
        }
        return AlunoMapping.convertModelToDto(alunoOptional.get());
    }

    public List<VoluntarioDTO> retornarVoluntarios() {
        List<Voluntario> voluntarios = voluntarioRepository.findAll();
        List<VoluntarioDTO> retorno = new ArrayList<>();
        voluntarios.forEach(v -> retorno.add(VoluntarioMapping.convertModelToDto(v)));
        return retorno;
    }

    public VoluntarioDTO retornarVoluntario(String voluntarioEmail) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findByEmail(voluntarioEmail);
        if(voluntarioOptional.isEmpty()) {
            throw new VoluntarioNaoExisteException("Voluntário não existe no nosso cadastro.");
        }
        return VoluntarioMapping.convertModelToDto(voluntarioOptional.get());
    }

    public List<ContatoDTO> retornarContatos() {
        List<Contato> contatos = contatoRepository.findAll();
        List<ContatoDTO> retorno = new ArrayList<>();
        contatos.forEach(c -> retorno.add(ContatoMapping.convertModelToDto(c)));
        return retorno;
    }

    public ContatoDTO retornarContato(String contatoEmail) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(contatoEmail);
        if(contatoOptional.isEmpty()) {
            throw new ContatoNaoExisteException("Contato não existe no nosso cadastro.");
        }
        return ContatoMapping.convertModelToDto(contatoOptional.get());
    }
}
