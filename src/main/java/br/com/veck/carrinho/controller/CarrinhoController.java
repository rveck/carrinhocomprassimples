package br.com.veck.carrinho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarrinhoController {
	
	@GetMapping("/")
    public String carrinho(Model model) {
        return "carrinho";
    }

}
