package br.com.carv.os.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.carv.os.domain.Tecnico;
import br.com.carv.os.domain.dtos.TecnicoDTO;
import br.com.carv.os.services.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Optional<Tecnico> tecnico = Optional.ofNullable(service.findById(id));
		return ResponseEntity.ok(new TecnicoDTO(tecnico.get()));
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<?> findByIdDTO(@PathVariable("id") Integer id) {
		Optional<TecnicoDTO> tecnico = Optional.ofNullable(service.findByIdDTO(id));
		return ResponseEntity.ok(tecnico.get());
		
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Tecnico> tecnicos = service.findAll();
		List<TecnicoDTO> tecnicosDTO = tecnicos.stream().map(tec -> new TecnicoDTO(tec)).collect(Collectors.toList());
		return ResponseEntity.ok(tecnicosDTO);	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<?> create(@Valid @RequestBody TecnicoDTO tecnico) {
		Tecnico tec = service.save(tecnico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tec.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody TecnicoDTO tecnico) {
		Tecnico tec = service.update(id, tecnico);
		return ResponseEntity.ok().body(new TecnicoDTO(tec));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
