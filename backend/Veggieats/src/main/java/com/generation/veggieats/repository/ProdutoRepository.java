package com.generation.veggieats.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.generation.veggieats.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
	
	@Query
	(value = "select * from tb_produto where preco between :inicio and :fim", nativeQuery = true)
		public List<Produto> findByPrecoBetween(BigDecimal inicio, BigDecimal fim);
	
	@Query
	(value = "select * from tb_produto where estoque between :inicio and :fim", nativeQuery = true)
		public List<Produto> findByEstoqueBetween(BigDecimal inicio, BigDecimal fim);
}
