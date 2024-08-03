package com.games.enterprisechallenge.repository;

import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Contato save(Contato contato);

    Optional<Contato> findByEmail(String email);

}
