package com.games.enterprisechallenge.controller;


import com.games.enterprisechallenge.model.dto.OficinaDTO;
import com.games.enterprisechallenge.service.OficinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @GetMapping("oficinas")
    public ResponseEntity<List<OficinaDTO>> retornaTodasOficinas() {
        return ResponseEntity.ok(this.oficinaService.retornaTodasOficinas());
    }
}
