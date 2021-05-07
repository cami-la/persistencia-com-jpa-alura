package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itens_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name="preco_unitario")
	private BigDecimal precoUnitario;
	
	private int quantidade;
	
	@ManyToOne //Muitos itens (Many) para um pedido (One)
	private Pedido pedido;
	@ManyToOne //
	private Produto produto;
	
	public ItemPedido() {
	}

	public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.precoUnitario = produto.getPreco();
		this.produto = produto;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
	}
	
	
}
