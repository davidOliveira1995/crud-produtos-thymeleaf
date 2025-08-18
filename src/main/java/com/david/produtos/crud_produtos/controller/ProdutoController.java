package com.david.produtos.crud_produtos.controller;

import com.david.produtos.crud_produtos.model.Produto;
import com.david.produtos.crud_produtos.repository.ProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto criar(@RequestBody  Produto produto) {


            if (produto.getNome().isEmpty()) {
                System.out.println("ERRO - O produto não tem nome!");
                throw new IllegalArgumentException("Parâmetro não informado!");
            }

            if (produto.getPreco() < 0) {
                System.out.println("ERRO - O valor do produto é menor que R$ 0,00.");
                throw new IllegalArgumentException("Preço do produto inválido!");
            }

        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {

        Optional<Produto> produtoOptional= produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado!");
        }

        return produtoOptional.get();
    }
@PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id,@RequestBody Produto produtoAtualizado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado!");
        }

        Produto produtoExistente = produtoOptional.get();
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado!");
        }

        produtoRepository.deleteById(id);
        System.out.println("Produto deletado com sucesso!");
    }
}
