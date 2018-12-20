package br.com.veck.carrinho.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.veck.carrinho.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
