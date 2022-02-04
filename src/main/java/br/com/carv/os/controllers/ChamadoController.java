package br.com.carv.os.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.carv.os.domain.Chamado;
import br.com.carv.os.domain.dtos.ChamadoDTO;
import br.com.carv.os.services.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {
	
	@Autowired
	private ChamadoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Chamado chamado = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
		
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Chamado> chamados = service.findAll();
		List<ChamadoDTO> chamadosDTO = chamados.stream().map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList());
		return ResponseEntity.ok().body(chamadosDTO);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> create(@Valid @RequestBody ChamadoDTO chamado) {
		Chamado chamadoNovo = service.save(chamado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/save/{id}").buildAndExpand(chamadoNovo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody ChamadoDTO chamado) {
		Chamado novoChamado = service.update(id, chamado);
		return ResponseEntity.ok().body(new ChamadoDTO(novoChamado));
		
	}

}
