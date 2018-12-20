package br.com.veck.carrinho.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.veck.carrinho.model.Produto;
import br.com.veck.carrinho.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void salvarProduto(Produto produto) {
		produtoRepository.save(produto);
	}
	
	public void removerProduto(Produto produto) {
		produtoRepository.delete(produto);
	}
	
	public Optional<Produto> buscarProdutoPorId(Long id) {
		return produtoRepository.findById(id);
	}
	
	public List<Produto> buscarTodos() {
		List<Produto> lstProduto = new ArrayList<Produto>();
		Iterable<Produto> it = produtoRepository.findAll();
		for (Produto produto: it) {
			lstProduto.add(produto);
		}
		return lstProduto;
	}

}
