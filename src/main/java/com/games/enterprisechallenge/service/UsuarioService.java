package com.games.enterprisechallenge.service;

import com.games.enterprisechallenge.exception.UsuarioJaExistenteException;
import com.games.enterprisechallenge.mapper.UsuarioMapper;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.model.dto.UsuarioDTO;
import com.games.enterprisechallenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(usuarioDto.getNomeUsuario());
        if(usuarioOptional.isPresent()) {
            throw new UsuarioJaExistenteException("Este nome de usuário já existe.");
        }
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Usuario usuario = usuarioRepository.save(UsuarioMapper.convertDtoToModel(usuarioDto));
        usuario.setSenha("");
        UsuarioDTO retornoDto = UsuarioMapper.convertModelToDto(usuario);
        return retornoDto;
    }
}
