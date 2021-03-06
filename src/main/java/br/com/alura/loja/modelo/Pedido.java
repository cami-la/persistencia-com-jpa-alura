package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY) //vários pedidos (Many) podem pertencer a um cliente (One);
	private Cliente cliente;
	

	@OneToMany (mappedBy="pedido", cascade = CascadeType.ALL) //um pedido (One) para muitos produtos (Many) 
	private List<ItemPedido> itens = new ArrayList<>();
	@OneToMany //um pedido (One) para muitos itens (Many) 
	private List<ItemPedido> produtos;

	
	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public List<ItemPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ItemPedido> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", valorTotal=" + valorTotal + ", data=" + data + ", cliente=" + cliente
				+ ", itens=" + itens + ", produtos=" + produtos + "]";
	}
		
	
}
