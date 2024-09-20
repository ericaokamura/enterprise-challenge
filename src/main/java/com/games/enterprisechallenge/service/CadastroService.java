package com.games.enterprisechallenge.service;

import com.games.enterprisechallenge.exception.*;
import com.games.enterprisechallenge.mapping.AlunoMapping;
import com.games.enterprisechallenge.mapping.ContatoMapping;
import com.games.enterprisechallenge.mapping.OficinaMapping;
import com.games.enterprisechallenge.mapping.VoluntarioMapping;
import com.games.enterprisechallenge.model.*;
import com.games.enterprisechallenge.model.dto.AlunoDTO;
import com.games.enterprisechallenge.model.dto.ContatoDTO;
import com.games.enterprisechallenge.model.dto.OficinaDTO;
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

    public AlunoDTO cadastrarAluno(AlunoDTO alunoDTO) {
        if(!alunoDTO.isAceitaTermo()) {
            throw new UsuarioNaoAceitouTermosException("Usuário não aceitou termos e condições de uso.");
        }
        Optional<Aluno> alunoOptional = alunoRepository.findByEmail(alunoDTO.getEmail());
        if(alunoOptional.isPresent()) {
            throw new AlunoJaExistenteException("Aluno já existente.");
        }
        Optional<Role> roleOptional = roleRepository.findById(alunoDTO.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }
        Optional<Oficina> oficinaOptional = oficinaRepository.findById(alunoDTO.getOficinaId());
        if(oficinaOptional.isEmpty()) {
            throw new OficinaNaoExistenteException("Oficina informada não existente.");
        }

        Aluno salvarAluno = AlunoMapping.convertDtoToModel(alunoDTO);
        salvarAluno.setSenha(passwordEncoder.encode(alunoDTO.getSenha()));
        salvarAluno.setRole(roleOptional.get());
        salvarAluno.setOficina(oficinaOptional.get());
        Aluno aluno = alunoRepository.save(salvarAluno);

        Usuario usuario = new Usuario();
        usuario.setEmail(alunoDTO.getEmail());
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(alunoDTO.getSenha()));
        usuario.setAceitaTermo(alunoDTO.isAceitaTermo());
        usuarioRepository.save(usuario);

        return AlunoMapping.convertModelToDto(aluno);
    }

    public ContatoDTO cadastrarContato(ContatoDTO dto) {
        if(!dto.isAceitaTermo()) {
            throw new UsuarioNaoAceitouTermosException("Usuário não aceitou termos e condições de uso.");
        }
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
        usuario.setAceitaTermo(dto.isAceitaTermo());
        usuarioRepository.save(usuario);

        return ContatoMapping.convertModelToDto(contato);
    }

    public VoluntarioDTO cadastrarVoluntario(VoluntarioDTO dto) {
        if(!dto.isAceitaTermo()) {
            throw new UsuarioNaoAceitouTermosException("Usuário não aceitou termos e condições de uso.");
        }
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
        usuario.setAceitaTermo(dto.isAceitaTermo());
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

    public AlunoDTO atualizarAluno(String alunoEmail, AlunoDTO alunoDTO) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmail(alunoEmail);
        if(alunoOptional.isEmpty()) {
            throw new AlunoNaoExisteException("Aluno não existe no nosso cadastro.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(alunoEmail);
        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExistenteException("Usuário não existente.");
        }

        Optional<Role> roleOptional = roleRepository.findById(alunoDTO.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }

        Optional<Oficina> oficinaOptional = oficinaRepository.findById(alunoDTO.getOficinaId());
        if(oficinaOptional.isEmpty()) {
            throw new OficinaNaoExistenteException("Oficina informada não existente.");
        }

        Aluno atualizarAluno = AlunoMapping.convertDtoToModel(alunoDTO);
        atualizarAluno.setSenha(passwordEncoder.encode(alunoDTO.getSenha()));
        atualizarAluno.setRole(roleOptional.get());
        atualizarAluno.setOficina(oficinaOptional.get());
        atualizarAluno.setId(alunoOptional.get().getId());
        atualizarAluno.setEmail(alunoOptional.get().getEmail());
        Aluno aluno = alunoRepository.save(atualizarAluno);

        Usuario usuario = usuarioOptional.get();
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(alunoDTO.getSenha()));
        usuarioRepository.save(usuario);

        return AlunoMapping.convertModelToDto(aluno);
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

    public OficinaDTO retornarOficinasUsuario(String usuarioEmail, String role) {
        Object usuario = retornaUsuario(usuarioEmail, role);
        if(usuario instanceof Aluno) {
            return OficinaMapping.convertModelToDto(((Aluno) usuario).getOficina());
        } else if (usuario instanceof Voluntario) {
            return OficinaMapping.convertModelToDto(((Voluntario) usuario).getOficina());
        }
        return null;
    }

    private Object retornaUsuario(String usuarioEmail, String roleName) {
        Object retorno = null;
        switch (roleName) {
            case "ROLE_ALUNO": {
                Optional<Aluno> aluno = alunoRepository.findByEmail(usuarioEmail);
                retorno = aluno.isPresent() ? aluno.get() : null;
                break;
            }
            case "ROLE_VOLUNTARIO": {
                Optional<Voluntario> voluntario = voluntarioRepository.findByEmail(usuarioEmail);
                retorno = voluntario.isPresent() ? voluntario.get() : null;
                break;
            }
            case "ROLE_CONTATO": {
                Optional<Contato> contato = contatoRepository.findByEmail(usuarioEmail);
                retorno = contato.isPresent() ? contato.get() : null;
                break;
            }
        }
        return retorno;
    }

    public VoluntarioDTO atualizarVoluntario(String voluntarioEmail, VoluntarioDTO voluntarioDTO) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findByEmail(voluntarioEmail);
        if(voluntarioOptional.isEmpty()) {
            throw new VoluntarioNaoExisteException("Voluntário não existente.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(voluntarioEmail);
        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExistenteException("Usuário não existente.");
        }

        Optional<Role> roleOptional = roleRepository.findById(voluntarioDTO.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }
        Optional<Oficina> oficinaOptional = oficinaRepository.findById(voluntarioDTO.getOficinaId());
        if(oficinaOptional.isEmpty()) {
            throw new OficinaNaoExistenteException("Oficina informada não existente.");
        }

        Voluntario atualizarVoluntario = VoluntarioMapping.convertDtoToModel(voluntarioDTO);
        atualizarVoluntario.setSenha(passwordEncoder.encode(voluntarioDTO.getSenha()));
        atualizarVoluntario.setRole(roleOptional.get());
        atualizarVoluntario.setOficina(oficinaOptional.get());
        atualizarVoluntario.setId(voluntarioOptional.get().getId());
        atualizarVoluntario.setEmail(voluntarioOptional.get().getEmail());
        Voluntario voluntario = voluntarioRepository.save(atualizarVoluntario);

        Usuario usuario = usuarioOptional.get();
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(voluntarioDTO.getSenha()));
        usuarioRepository.save(usuario);

        return VoluntarioMapping.convertModelToDto(voluntario);
    }

    public ContatoDTO atualizarContato(String contatoEmail, ContatoDTO contatoDTO) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(contatoEmail);
        if(contatoOptional.isEmpty()) {
            throw new ContatoNaoExisteException("Contato não existente.");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(contatoEmail);
        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExistenteException("Usuário não existente.");
        }

        Optional<Role> roleOptional = roleRepository.findById(contatoDTO.getRoleId());
        if(roleOptional.isEmpty()) {
            throw new RoleNaoExistenteException("Role informada não existente.");
        }

        Contato atualizarContato = ContatoMapping.convertDtoToModel(contatoDTO);
        atualizarContato.setSenha(passwordEncoder.encode(contatoDTO.getSenha()));
        atualizarContato.setRole(roleOptional.get());
        atualizarContato.setId(contatoOptional.get().getId());
        atualizarContato.setEmail(contatoOptional.get().getEmail());
        Contato contato = contatoRepository.save(atualizarContato);

        Usuario usuario = usuarioOptional.get();
        usuario.setRole(roleOptional.get());
        usuario.setSenha(passwordEncoder.encode(contatoDTO.getSenha()));
        usuarioRepository.save(usuario);

        return ContatoMapping.convertModelToDto(contato);
    }

    public Void deletarContato(String contatoEmail) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(contatoEmail);
        if(contatoOptional.isEmpty()) {
            throw new ContatoNaoExisteException("Contato não existente.");
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(contatoEmail);
        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExistenteException("Usuário não existente.");
        }
        contatoRepository.delete(contatoOptional.get());
        usuarioRepository.delete(usuarioOptional.get());
        return null;
    }

    public Void deletarVoluntario(String voluntarioEmail) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findByEmail(voluntarioEmail);
        if(voluntarioOptional.isEmpty()) {
            throw new VoluntarioNaoExisteException("Voluntário não existente.");
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(voluntarioEmail);
        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExistenteException("Usuário não existente.");
        }
        voluntarioRepository.delete(voluntarioOptional.get());
        usuarioRepository.delete(usuarioOptional.get());
        return null;
    }

    public Void deletarAluno(String alunoEmail) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmail(alunoEmail);
        if(alunoOptional.isEmpty()) {
            throw new AlunoNaoExisteException("Aluno não existente.");
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(alunoEmail);
        if(usuarioOptional.isEmpty()) {
            throw new UsuarioNaoExistenteException("Usuário não existente.");
        }
        alunoRepository.delete(alunoOptional.get());
        usuarioRepository.delete(usuarioOptional.get());
        return null;
    }
}
