package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();		
		
		populaBancoDeDados();
		
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscaPorId(1l);		

		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto1 = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);

		produtoDao.cadastrar(produto1);
		produtoDao.cadastrar(produto2);
		produtoDao.cadastrar(produto3);	
		
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(10, pedido1, produto1));
		pedido1.adicionarItem(new ItemPedido(40, pedido1, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(2, pedido2, produto3));
		
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrarPedido(pedido1);
		pedidoDao.cadastrarPedido(pedido2);

		
		BigDecimal valorTotal = pedidoDao.valorTotalVendido();
		System.out.println("Valor total: R$" + valorTotal);
		
		
		List<RelatorioDeVendasVo> relatorioDeVendas = pedidoDao.relatorioDeVendas();
		relatorioDeVendas.forEach(System.out::println);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void populaBancoDeDados() {
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Iphone 11", "Iphone 11", new BigDecimal("1000.0"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("2000.0"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("12000.0"), informatica);

		
		ItemPedido itemPedido = new ItemPedido();
		
		Cliente cliente = new Cliente("Camila", "123456789");

		CategoriaDao categoriaDao = new CategoriaDao(em);
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);		

		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		ClienteDao clienteDao = new ClienteDao(em);
		clienteDao.cadastroCliente(cliente);
		
		em.getTransaction().commit();
		
		em.close();		
		
	}

}
