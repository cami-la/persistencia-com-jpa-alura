package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="informatica")
public class Informatica extends Produto {
	
	private String marca;
	private Integer modelo;
	
	
	public Informatica() {
	}

	public Informatica(String marca, Integer modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}
	public Integer getModelo() {
		return modelo;
	}
	
	
	
}
