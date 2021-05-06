package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadstroDePedido {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();		
		
		populaBancoDeDados();

		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(1l);
		produtoDao.cadastrar(produto);
		
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscaPorId(1l);		
		
		
		Pedido pedido = new Pedido(cliente);
		PedidoDao pedidoDao = new PedidoDao(em);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedidoDao.cadastrarPedido(pedido);
		
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void populaBancoDeDados() {
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		Categoria categoria = new Categoria("CELULARES");
		
		Produto produto = new Produto("Iphone 11", "Iphone 11 - Camila", new BigDecimal("5000.0"), categoria);
		
		ItemPedido itemPedido = new ItemPedido();
		
		Cliente cliente = new Cliente("Camila", "123456789");

		CategoriaDao categoriaDao = new CategoriaDao(em);
		categoriaDao.cadastrar(categoria);

		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.cadastrar(produto);
		
		ClienteDao clienteDao = new ClienteDao(em);
		clienteDao.cadastroCliente(cliente);
		
		em.getTransaction().commit();
		
		em.close();		
		
	}

}
