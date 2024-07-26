package com.games.enterprisechallenge.repository;

import com.games.enterprisechallenge.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Contato save(Contato contato);
}
