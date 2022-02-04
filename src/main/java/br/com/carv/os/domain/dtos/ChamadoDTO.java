package br.com.carv.os.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.carv.os.domain.Chamado;

public class ChamadoDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Integer id; 
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento; 
	
	@NotNull(message = "O campo prioridade é obrigatório!")
	private Integer prioridadeId; 
	@NotNull(message = "O campo status é obrigatório!")
	private Integer statusId; 
	@NotNull(message = "O campo título é obrigatório!")
	private String titulo; 
	@NotNull(message = "O campo observações é obrigatório!")
	private String observacoes;
	@NotNull(message = "O campo tecnico é obrigatório!")
	private Integer tecnicoId; 
	@NotNull(message = "O campo cliente é obrigatório!")
	private Integer clienteId;
	private String nomeTecnico;
	private String nomeCliente;
	
	public ChamadoDTO() {
		super();

	}
	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.prioridadeId = chamado.getPrioridade().getCodigo();
		this.statusId = chamado.getStatus().getCodigo();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.tecnicoId = chamado.getTecnico().getId();
		this.clienteId = chamado.getCliente().getId();
		this.nomeTecnico = chamado.getTecnico().getNome();
		this.nomeCliente = chamado.getCliente().getNome();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDate getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Integer getPrioridadeId() {
		return prioridadeId;
	}
	public void setPrioridadeId(Integer prioridadeId) {
		this.prioridadeId = prioridadeId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Integer getTecnicoId() {
		return tecnicoId;
	}
	public void setTecnicoId(Integer tecnicoId) {
		this.tecnicoId = tecnicoId;
	}
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public String getNomeTecnico() {
		return nomeTecnico;
	}
	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	
	
	

}
