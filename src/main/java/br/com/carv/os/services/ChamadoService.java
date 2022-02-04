package br.com.carv.os.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carv.os.domain.Chamado;
import br.com.carv.os.domain.Cliente;
import br.com.carv.os.domain.Tecnico;
import br.com.carv.os.domain.dtos.ChamadoDTO;
import br.com.carv.os.domain.enums.Prioridade;
import br.com.carv.os.domain.enums.Status;
import br.com.carv.os.exceptions.NotFoundException;
import br.com.carv.os.repositories.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new NotFoundException("Chamado não encontrado. Id: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado save(ChamadoDTO chamado) {
		return chamadoRepository.save(novoChamado(chamado));
	}
	
	private Chamado novoChamado(ChamadoDTO chamado) {
		
		Tecnico tecnico = tecnicoService.findById(chamado.getTecnicoId());
		Cliente cliente = clienteService.findById(chamado.getClienteId());
		
		Chamado novoChamado = new Chamado();
		
		if(chamado.getId() != null) {
			chamado.setId(chamado.getId());
		}
		
		if(chamado.getStatusId().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		novoChamado.setTecnico(tecnico);
		novoChamado.setCliente(cliente);
		novoChamado.setPrioridade(Prioridade.toEnum(chamado.getPrioridadeId()));
		novoChamado.setStatus(Status.toEnum(chamado.getStatusId()));
		novoChamado.setTitulo(chamado.getTitulo());
		novoChamado.setObservacoes(chamado.getObservacoes());
		return novoChamado;
		
	}

	public Chamado update(Integer id, ChamadoDTO chamado) {
		chamado.setId(id);
		Chamado velhoChamado = findById(id); 
		velhoChamado = novoChamado(chamado);
		return chamadoRepository.save(velhoChamado);
	}
}
