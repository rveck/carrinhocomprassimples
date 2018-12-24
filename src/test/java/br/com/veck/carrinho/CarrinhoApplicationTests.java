package br.com.veck.carrinho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.veck.carrinho.controller.CarrinhoController;
import br.com.veck.carrinho.controller.RestCarrinhoController;
import br.com.veck.carrinho.controller.RestProdutoController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarrinhoApplicationTests {
	
	@Autowired
    private RestCarrinhoController restCarrinhoController;
	
	@Autowired 
	private RestProdutoController restProdutoController;
	
	@Autowired
	private CarrinhoController carrinhoController;
    
    @Autowired
    private MockMvc mockMvc;

	@Test
	public void contextLoads() {
		assertThat(restCarrinhoController).isNotNull();
		assertThat(restProdutoController).isNotNull();
		assertThat(carrinhoController).isNotNull();
	}
	
	@Test
    public void retornoRests() throws Exception {
        this.mockMvc.perform(get("/carrinho"))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(content().string(containsString("codigo")));            
                
        this.mockMvc.perform(get("/produto"))
	    	.andDo(print())
	    	.andExpect(status().isOk())
	        .andExpect(content().string(containsString("codigo")));	        
    }
}

