package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		List<Produto> buscarPorParametrosComCriteria = produtoDao.buscarPorParametrosComCriteria("Iphone", null, null);
		
		buscarPorParametrosComCriteria.forEach(System.out::println);
	}
	
	public static void popularBancoDeDados() {
		//criar 3 categorias e 3 produtos
		Categoria categoria = new Categoria("CELULARES");
		Categoria categoria1 = new Categoria("VIDEOGAMES");
		Categoria categoria2 = new Categoria("INFORMATICA");
		
		Produto produto = new Produto("Iphone", "Iphone 11", new BigDecimal("5000.0"), categoria);
		Produto produto1 = new Produto("XboX", "XBoX one", new BigDecimal("6000.0"), categoria1);
		Produto produto2 = new Produto("Macbok", "Macbook Pro", new BigDecimal("12000.0"), categoria2);

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		categoriaDao.cadastrarCategoria(categoria);
		categoriaDao.cadastrarCategoria(categoria1);
		categoriaDao.cadastrarCategoria(categoria2);
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.cadastrarProduto(produto);
		produtoDao.cadastrarProduto(produto1);
		produtoDao.cadastrarProduto(produto2);
		
		em.getTransaction().commit();
		em.close();
		
	}

}
