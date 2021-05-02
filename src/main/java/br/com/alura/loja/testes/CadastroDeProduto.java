package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();	
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		System.out.println(produto.getPreco());
		
		List<Produto> buscarTodos = produtoDao.buscarTodos();
		buscarTodos.forEach(p -> System.out.println(p.getDescricao()));
		
		List<Produto> buscarPorNome = produtoDao.buscarPorNome("Xiomi Redmi");
		buscarPorNome.forEach(p -> System.out.println(p.getId()));
		
		List<Produto> buscarPorNomeDaCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		buscarPorNomeDaCategoria.forEach(p -> System.out.println(p.getDataCadastro()));
		
	}
	

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
			
		Produto produto = new Produto("Xiomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();		
		em.getTransaction().begin();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(produto);
		
		em.getTransaction().commit();
		em.close();
		
	}
}
