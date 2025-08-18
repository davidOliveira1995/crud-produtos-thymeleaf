package com.david.produtos.crud_produtos.repository;

import com.david.produtos.crud_produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    public Produto save(Produto produto);
    public List<Produto> findAll();
    public Optional<Produto> findById(Long id);
    public void deleteById(Long id);
}
