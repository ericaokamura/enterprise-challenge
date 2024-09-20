package com.games.enterprisechallenge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OficinaDTO {

    private Long id;
    private String nomeOficina;
    private String horarios;

}
