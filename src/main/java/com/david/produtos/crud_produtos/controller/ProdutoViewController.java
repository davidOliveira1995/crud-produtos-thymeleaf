package com.david.produtos.crud_produtos.controller;

import com.david.produtos.crud_produtos.model.Produto;
import com.david.produtos.crud_produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos-view")
public class ProdutoViewController {
    private final ProdutoRepository produtoRepository;

    public ProdutoViewController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("produto", new Produto()); // usado no form
        return "produtos"; // nome do arquivo HTML em /templates
    }

    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos-view";
    }
}
