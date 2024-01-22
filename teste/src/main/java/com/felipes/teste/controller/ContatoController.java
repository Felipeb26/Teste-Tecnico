package com.felipes.teste.controller;

import com.felipes.teste.domain.dto.ContatoDTO;
import com.felipes.teste.service.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/contato", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping
    public List<ContatoDTO> findAllContatos() {
        return contatoService.findAll();
    }

    @GetMapping("/{id}")
    public ContatoDTO findContato(@PathVariable Long id) {
        return contatoService.findById(id);
    }

    @PostMapping
    public ContatoDTO saveContato(@RequestBody @Valid ContatoDTO contatoDTO) {
        return contatoService.save(contatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ContatoDTO updateContato(@PathVariable Long id, @RequestBody @Valid ContatoDTO contatoDTO) {
        return contatoService.update(id, contatoDTO);
    }
}
