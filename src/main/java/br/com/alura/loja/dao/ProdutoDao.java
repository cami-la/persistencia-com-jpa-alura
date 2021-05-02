package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return this.em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return this.em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nomeCategoria) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
		return this.em.createQuery(jpql, Produto.class).setParameter(1, nomeCategoria).getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nomeProduto) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
		return this.em.createQuery(jpql, BigDecimal.class)
				.setParameter(1, nomeProduto)
				.getSingleResult();
	}
}
