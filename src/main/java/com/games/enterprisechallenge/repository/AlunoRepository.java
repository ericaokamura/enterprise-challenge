package com.games.enterprisechallenge.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.games.enterprisechallenge.model.Aluno;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno save(Aluno aluno);
    Optional<Aluno> findByEmail(String email);

}
