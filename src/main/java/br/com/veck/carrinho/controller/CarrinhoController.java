package br.com.veck.carrinho.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarrinhoController {
	
	@Value("${server.url}")
	public String serverUrl;
	
	@Value("${server.port}")
	public String serverPort;
	
	@GetMapping("/")
    public String carrinho(Model model) {
		model.addAttribute("serverUrl", serverUrl);
		model.addAttribute("serverPort", serverPort);
        return "carrinho";
    }

}
