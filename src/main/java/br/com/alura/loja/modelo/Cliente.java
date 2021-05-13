package br.com.alura.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private DadosPessoais dadosPessoais;
	
	
	public Cliente() {
	}

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
	
	public String getNomeCliente() {
		return this.dadosPessoais.getNome();
	}
	
	public String getCpfCliente() {
		return this.dadosPessoais.getNome();
	}
	
	
}
