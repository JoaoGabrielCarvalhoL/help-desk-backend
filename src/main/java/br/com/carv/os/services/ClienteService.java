package br.com.carv.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carv.os.domain.Cliente;
import br.com.carv.os.domain.Pessoa;
import br.com.carv.os.domain.dtos.ClienteDTO;
import br.com.carv.os.exceptions.DataIntegrityViolationException;
import br.com.carv.os.exceptions.NotFoundException;
import br.com.carv.os.repositories.ClienteRepository;
import br.com.carv.os.repositories.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> Cliente = ClienteRepository.findById(id); 
		
		return Cliente.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
	}
	
	public ClienteDTO findByIdDTO(Integer id) {
		Optional<Cliente> Cliente = Optional.ofNullable(ClienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"))); 
		ClienteDTO dto = new ClienteDTO(Cliente.get());

		return dto;
		
	}
	
	public List<Cliente> findAll() {
		return ClienteRepository.findAll();
	}
	
	public Cliente save(ClienteDTO Cliente) {
		Cliente.setId(null);
		Cliente.setSenha(encoder.encode(Cliente.getSenha()));
		validaCpfEEmail(Cliente);
		Cliente tec = new Cliente(Cliente);
		return ClienteRepository.save(tec);
		
	}

	private void validaCpfEEmail(ClienteDTO Cliente) {
		Optional<Pessoa> user = pessoaRepository.findByCpf(Cliente.getCpf());
		
		if (user.isPresent() && user.get().getId() != Cliente.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no sistema!");
		}
		
		user = pessoaRepository.findByEmail(Cliente.getEmail());
		
		if (user.isPresent() && user.get().getId() != Cliente.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
	}

	public Cliente update(Integer id, @Valid ClienteDTO Cliente) {
		Cliente.setId(id);
		Cliente ClienteOld = findById(id);
		
		if(!Cliente.getSenha().equals(ClienteOld.getSenha()) ) {
			Cliente.setSenha(encoder.encode(Cliente.getSenha()));
		}
		
		validaCpfEEmail(Cliente);
		ClienteOld = new Cliente(Cliente);
		return ClienteRepository.save(ClienteOld);
	}

	public void delete(Integer id) {
		Cliente tec = findById(id); 
		
		if(tec.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado");
			
		}
		
		ClienteRepository.deleteById(id);
		
	}
	
}
