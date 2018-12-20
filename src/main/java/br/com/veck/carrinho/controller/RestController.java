package br.com.veck.carrinho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.veck.carrinho.model.Carrinho;
import br.com.veck.carrinho.model.Produto;
import br.com.veck.carrinho.service.ProdutoService;

@Controller
public class RestController {
	
	@Autowired
	private ProdutoService produtoService;
	
	private List<Carrinho> itensCarrinho;
	
	@RequestMapping(path="/produto", method = RequestMethod.GET)
	public @ResponseBody List<Produto> consultarTodos() {
		return produtoService.buscarTodos();
	}

}
