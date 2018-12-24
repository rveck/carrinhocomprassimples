package br.com.veck.carrinho.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.veck.carrinho.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	@Override
	@Query("SELECT p FROM Produto p WHERE p.dataExclusao IS NULL AND p.id = :id")
	public Optional<Produto> findById(@Param("id") Long id);
	
	@Query("SELECT p FROM Produto p WHERE p.dataExclusao IS NULL")
	public List<Produto> buscarTodosProdutos();

}
