package com.games.enterprisechallenge.repository;

import com.games.enterprisechallenge.model.Oficina;
import com.games.enterprisechallenge.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
