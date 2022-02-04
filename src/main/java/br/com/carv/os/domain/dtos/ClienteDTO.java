package br.com.carv.os.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.carv.os.domain.Cliente;
import br.com.carv.os.domain.enums.Perfil;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Integer id; 
	
	@NotNull(message = "O campo nome é obrigatório!")
	protected String nome; 
	@NotNull(message = "O campo cpf é obrigatório!")
	protected String cpf; 
	@NotNull(message = "O campo email é obrigatório!")
	protected String email; 
	protected String senha; 
	protected Set<Integer> perfis = new HashSet<>(); 
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCadastro = LocalDate.now();
	
	public ClienteDTO() {
		super();
		
	}

	public ClienteDTO(Cliente Cliente) {
		super();
		this.id = Cliente.getId();
		this.nome = Cliente.getNome();
		this.cpf = Cliente.getCpf();
		this.email = Cliente.getEmail();
		this.senha = Cliente.getSenha();
		this.perfis = Cliente.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCadastro = Cliente.getDataCadastro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void setPerfis(Perfil perfis) {
		this.perfis.add(perfis.getCodigo());
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha
				+ ", perfis=" + perfis + ", dataCadastro=" + dataCadastro + "]";
	}
	
	
	
	

}
