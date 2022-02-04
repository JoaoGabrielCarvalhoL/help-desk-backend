package br.com.carv.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carv.os.domain.Pessoa;
import br.com.carv.os.domain.Tecnico;
import br.com.carv.os.domain.dtos.TecnicoDTO;
import br.com.carv.os.exceptions.DataIntegrityViolationException;
import br.com.carv.os.exceptions.NotFoundException;
import br.com.carv.os.repositories.PessoaRepository;
import br.com.carv.os.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id); 
		
		return tecnico.orElseThrow(() -> new NotFoundException("Tecnico não encontrado"));
	}
	
	public TecnicoDTO findByIdDTO(Integer id) {
		Optional<Tecnico> tecnico = Optional.ofNullable(tecnicoRepository.findById(id).orElseThrow(() -> new NotFoundException("Tecnico não encontrado"))); 
		TecnicoDTO dto = new TecnicoDTO(tecnico.get());

		return dto;
		
	}
	
	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico save(TecnicoDTO tecnico) {
		tecnico.setId(null);
		tecnico.setSenha(encoder.encode(tecnico.getSenha()));
		validaCpfEEmail(tecnico);
		Tecnico tec = new Tecnico(tecnico);
		return tecnicoRepository.save(tec);
		
	}

	private void validaCpfEEmail(TecnicoDTO tecnico) {
		Optional<Pessoa> user = pessoaRepository.findByCpf(tecnico.getCpf());
		
		if (user.isPresent() && user.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no sistema!");
		}
		
		user = pessoaRepository.findByEmail(tecnico.getEmail());
		
		if (user.isPresent() && user.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnico) {
		tecnico.setId(id);
		Tecnico tecnicoOld = findById(id);
		
		if(!tecnico.getSenha().equals(tecnicoOld.getSenha()) ) {
			tecnico.setSenha(encoder.encode(tecnico.getSenha()));
		}
		
		validaCpfEEmail(tecnico);
		tecnicoOld = new Tecnico(tecnico);
		return tecnicoRepository.save(tecnicoOld);
	}

	public void delete(Integer id) {
		Tecnico tec = findById(id); 
		
		if(tec.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado");
			
		}
		
		tecnicoRepository.deleteById(id);
		
	}
	
}
