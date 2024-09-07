package com.games.enterprisechallenge.repository;

import com.games.enterprisechallenge.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {

}
