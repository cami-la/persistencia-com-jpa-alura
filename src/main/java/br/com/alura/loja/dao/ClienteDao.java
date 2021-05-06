package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {
	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastroCliente(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente buscaPorId(long id) {
		return this.em.find(Cliente.class, id);
	}
	
}
