package br.com.veck.carrinho.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.veck.carrinho.exception.CarrinhoException;
import br.com.veck.carrinho.model.Produto;
import br.com.veck.carrinho.rest.Resposta;
import br.com.veck.carrinho.service.ProdutoService;
import br.com.veck.carrinho.util.Constantes;

@Controller
public class RestCarrinhoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
    private MessageSource messageSource;
	
	Logger logger = LoggerFactory.getLogger(RestCarrinhoController.class);
	
	private List<Produto> carrinho = new ArrayList<Produto>();
	
	@RequestMapping(path=Constantes.Url.URL_CARRINHO, method = RequestMethod.GET)
	public @ResponseBody Resposta consultarTodos() {
		Resposta resposta = new Resposta();
		if (carrinho.isEmpty()) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(messageSource.getMessage("carrinho.erro.vazio", null, Locale.getDefault()));
		}else {
			resposta.setResposta(carrinho);	
		}		
		logger.info("Consultando todos os produtos no carrinho");
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CARRINHO + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resposta consultarProdutoPorId(@PathVariable Long id) {
		Resposta resposta = new Resposta();
		List<Produto> lstProduto = new ArrayList<Produto>();
		for (Produto produto : carrinho) {
			if (produto.getId() == id) {
				lstProduto.add(produto);
			}
		}	
		if(lstProduto.isEmpty()) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(String.format(messageSource.getMessage("produto.erro.produto.naoencontrado", null, Locale.getDefault()), id));
		}else {
			resposta.setResposta(lstProduto);	
		}			 
		logger.info(String.format("Consultando produto %s no carrinho", id));
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CARRINHO, method = RequestMethod.POST)
	public @ResponseBody Resposta adicionarProduto(@RequestBody Produto produto) {
		Resposta resposta = new Resposta();
		try {
			Long id = produto .getId();			
			if (id == null || id == 0) {
				resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
				resposta.setMensagem(String.format(messageSource.getMessage("campo.obrigatorio", null, Locale.getDefault()), 
						messageSource.getMessage("campo.id", null, Locale.getDefault())));
			}else {
				Produto produtoJaAdicionado = null;
				for (Produto p : carrinho) {
					if (p.getId() == id) {
						produtoJaAdicionado = p;
						break;
					}
				}
				if (produtoJaAdicionado == null) {
					Produto produtoSalvo = produtoService.buscarProdutoPorId(id);
					carrinho.add(produtoSalvo);
					resposta.setResposta(carrinho);
				}else {
					resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
					resposta.setMensagem(messageSource.getMessage("carrinho.erro.produto.ja.adicionado", null, Locale.getDefault()));
				}								 
			}			
			logger.info(String.format("Adicionando produto %s no carrinho", produto.getNome()));
		}catch(CarrinhoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CARRINHO + "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Resposta atualizarQuantidadeProduto(@RequestBody Produto produto, @PathVariable Long id) {
		Resposta resposta = new Resposta();		
		Produto produtoInformado = null;
		for (Produto p : carrinho) {
			if (p.getId() == id) {
				produtoInformado = p;
				break;
			}
		}
		if (produtoInformado == null) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(messageSource.getMessage("carrinho.erro.produto.nao.encontrado.para.atualizacao", null, Locale.getDefault()));
		}else {
			Integer novaQtd = produto.getQuantidade();
			if (novaQtd == 0) {
				carrinho.remove(produtoInformado);
			}else {
				produtoInformado.setQuantidade(novaQtd);
			}
		}
		logger.info(String.format("Atualizando a quantidade do produto %s no carrinho", id));		
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CARRINHO + "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Resposta removerProdutoCarrinho(@PathVariable Long id) {
		Resposta resposta = new Resposta();	
		Produto produtoSelecionado = null;
		if (id == null || id == 0) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(String.format(messageSource.getMessage("campo.obrigatorio", null, Locale.getDefault()), 
					messageSource.getMessage("campo.id", null, Locale.getDefault())));
		}else {
			for (Produto p : carrinho) {
				if (p.getId() == id) {
					produtoSelecionado = p;
					break;
				}
			}
			if (produtoSelecionado == null) {
				resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
				resposta.setMensagem(messageSource.getMessage("carrinho.erro.produto.nao.encontrado", null, Locale.getDefault()));
			}else {
				carrinho.remove(produtoSelecionado);
			}
		}
		logger.info(String.format("Removendo produto %s", id));
		return resposta;
	}

}
