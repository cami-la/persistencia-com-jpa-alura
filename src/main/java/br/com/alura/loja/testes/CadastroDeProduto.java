package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
				
		EntityManager em = JPAUtil.getEntityManager();		
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("Iphone 11 Pro");
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.setNome("Iphone 11 Pro Max");
		em.flush();
		//em.clear();
		
		em.remove(celulares);
		em.flush();
		
	}
}
