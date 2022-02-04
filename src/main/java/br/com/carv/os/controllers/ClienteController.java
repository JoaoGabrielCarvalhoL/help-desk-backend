package br.com.carv.os.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.carv.os.domain.Cliente;
import br.com.carv.os.domain.dtos.ClienteDTO;
import br.com.carv.os.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Optional<Cliente> Cliente = Optional.ofNullable(service.findById(id));
		return ResponseEntity.ok(new ClienteDTO(Cliente.get()));
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<?> findByIdDTO(@PathVariable("id") Integer id) {
		Optional<ClienteDTO> Cliente = Optional.ofNullable(service.findByIdDTO(id));
		return ResponseEntity.ok(Cliente.get());
		
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Cliente> Clientes = service.findAll();
		List<ClienteDTO> ClientesDTO = Clientes.stream().map(tec -> new ClienteDTO(tec)).collect(Collectors.toList());
		return ResponseEntity.ok(ClientesDTO);	
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> create(@Valid @RequestBody ClienteDTO Cliente) {
		Cliente tec = service.save(Cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tec.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO Cliente) {
		Cliente tec = service.update(id, Cliente);
		return ResponseEntity.ok().body(new ClienteDTO(tec));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
