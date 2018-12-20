package br.com.veck.carrinho.model;

import java.util.List;

public class Carrinho {
	
	private List<Produto> itens;

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

}
