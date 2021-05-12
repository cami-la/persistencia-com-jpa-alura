package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrarProduto(Produto produto) {
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
		return this.em.createNamedQuery("Produto.buscarPorNomeDaCategoria", Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nomeProduto) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
		return this.em.createQuery(jpql, BigDecimal.class)
				.setParameter(1, nomeProduto)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
		if (nome != null && nome.trim().isEmpty()) jpql = " AND p.nome = :nome ";
		if (preco != null && nome.trim().isEmpty()) jpql = " AND p.preco = :preco ";
		if (dataCadastro != null && nome.trim().isEmpty()) jpql = " AND p.dataCadastro = :dataCadastro ";
		
		TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class);
		if (nome != null && nome.trim().isEmpty()) query.setParameter("nome", nome);
		if (preco != null && nome.trim().isEmpty()) query.setParameter("preco", nome);
		if (dataCadastro != null && nome.trim().isEmpty()) query.setParameter("dataCadastro", nome);
		
		return query.getResultList();
	}
	
	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if (nome != null && nome.trim().isEmpty()) filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		if (preco != null && nome.trim().isEmpty()) filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		if (dataCadastro != null && nome.trim().isEmpty()) filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));

		query.where(filtros);
		return this.em.createQuery(query).getResultList();
		
	}
	
}
