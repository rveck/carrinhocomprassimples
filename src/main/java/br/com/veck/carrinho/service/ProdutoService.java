package br.com.veck.carrinho.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.veck.carrinho.exception.CarrinhoException;
import br.com.veck.carrinho.model.Produto;
import br.com.veck.carrinho.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
    private MessageSource messageSource;
	
	public void salvarProduto(Produto produto) throws CarrinhoException{
		try {
			produtoRepository.save(produto);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CarrinhoException(String.format(messageSource.getMessage("produto.erro.salvamento", null, Locale.getDefault()), produto.getNome()),e);
		}
	}
	
	public void removerProduto(Long id) throws CarrinhoException{
		try {
			Produto produto = buscarProdutoPorId(id);
			produtoRepository.delete(produto);
		}catch(CarrinhoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CarrinhoException(String.format(messageSource.getMessage("produto.erro.remocao", null, Locale.getDefault()), id),e);
		}
	}
	
	public Produto buscarProdutoPorId(Long id) throws CarrinhoException{
		try {
			Optional<Produto> produto = produtoRepository.findById(id);
			if (produto.isPresent()) {
				return produto.get();
			}else {
				throw new CarrinhoException(String.format(messageSource.getMessage("produto.erro.produto.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(CarrinhoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CarrinhoException(String.format(messageSource.getMessage("produto.erro.consulta", null, Locale.getDefault()), id),e);
		}
	}
	
	public List<Produto> buscarTodos() throws CarrinhoException{
		try {
			List<Produto> lstProduto = new ArrayList<Produto>();
			Iterable<Produto> it = produtoRepository.findAll();
			for (Produto produto: it) {
				lstProduto.add(produto);
			}
			if (lstProduto.isEmpty()) {
				throw new CarrinhoException(messageSource.getMessage("produto.erro.nenhum.encontrado", null, Locale.getDefault()),null);
			}
			return lstProduto;
		}catch(CarrinhoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CarrinhoException(messageSource.getMessage("produto.erro.nenhum.encontrado", null, Locale.getDefault()),e);
		}
	}

}
