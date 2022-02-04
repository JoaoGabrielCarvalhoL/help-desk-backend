package br.com.carv.os.domain.enums;

public enum Status {
	
	BAIXA(0, "BAIXA"), 
	MEDIA(1, "MEDIA"), 
	ALTA(2, "ALTA");
	
	private Integer codigo; 
	private String descricao;
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo; 
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Status perfil : Status.values()) {
			if (cod.equals(perfil.getCodigo())) {
				return perfil;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida!");
	}

}
