package com.games.enterprisechallenge.repository;

import com.games.enterprisechallenge.model.dto.VoluntarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.games.enterprisechallenge.model.Voluntario;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

    Voluntario save(Voluntario voluntario);
}