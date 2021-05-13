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

public class PerformaceConsultas {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		popularBancoDeDados();
		
		/*ClienteDao clienteDao = new ClienteDao(em);
		Cliente buscaPorId = clienteDao.buscaPorId(1l);
		System.out.println(buscaPorId);*/
		
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido buscarPorIdPedido = pedidoDao.buscarPorId(1l);
		System.out.println(buscarPorIdPedido.getData());
		
		Pedido buscarPedidoComCliente = pedidoDao.buscarPedidoComCliente(1l);

		em.getTransaction().commit();
		
		em.close();
		System.out.println(buscarPorIdPedido.getCliente().getNomeCliente());
	}

	private static void popularBancoDeDados() {
		EntityManager em = JPAUtil.getEntityManager(); //Ponte para o banco de dados
		
		//instaciando as classes
		Cliente cliente = new Cliente("Camila", "01234");

		
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto produto = new Produto("Iphone", "Iphone 11", new BigDecimal("5000.0"), celulares);
		Produto produto1 = new Produto("Xbox", "Xbox One", new BigDecimal("6000.0"), videogames);
		Produto produto2 = new Produto("Macbook", "Macbook Pro", new BigDecimal("8000.0"), informatica);
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(2, pedido1, produto));
		pedido1.adicionarItem(new ItemPedido(3, pedido1, produto1));
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(5, pedido2, produto2));
		
		
		//persistindo no banco de Dados
		ClienteDao clienteDao = new ClienteDao(em);
		clienteDao.cadastroCliente(cliente);
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrarPedido(pedido1);
		pedidoDao.cadastrarPedido(pedido2);
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		categoriaDao.cadastrarCategoria(celulares);
		categoriaDao.cadastrarCategoria(videogames);
		categoriaDao.cadastrarCategoria(informatica);

		
		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.cadastrarProduto(produto);
		produtoDao.cadastrarProduto(produto1);
		produtoDao.cadastrarProduto(produto2);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();

	}
	
	

}
