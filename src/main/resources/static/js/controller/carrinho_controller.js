'use strict';
 
var App = angular.module('carrinhoApp');

App.controller('CarrinhoController', ['$scope', 'CarrinhoService', 'ProdutoService', 
	function($scope, CarrinhoService, ProdutoService) {
    
	var self = this;
	
	//funcoes	
	self.filtrar = filtrar;
	self.setProduto = setProduto;
	self.adicionarProduto = adicionarProduto;
	self.removerProduto = removerProduto;
	self.atualizarProduto = atualizarProduto;
	self.limparCarrinho = limparCarrinho;
    
    inicializar();
    
    function inicializar(){
    	
    	self.produto = {
    			id : null,
    			nome : '',
    			valor : 0,
    			quantidade : 0
    	};
    	
    	self.qtdTotal = 0;    	
    	self.valorTotal = 0;
    	self.focus = false;    	
        self.busca = '';        
        self.carrinho = [];
        self.produtosCadastrados = [];
        self.produtosFiltrados = [];
        self.msgCarrinho = '';
        self.msgSucesso = '';
        self.msgErro = '';
        
    	listarCarrinho();
    	carregarProdutosCadastrados();    	
    }
 
    function listarCarrinho(){
        CarrinhoService.listarCarrinho()
            .then(
            function(d) {
            	if (d.codigo == 0){
            		self.carrinho = d.resposta;
            		self.valorTotal = d.valorTotal;
            		self.qtdTotal = d.qtdTotal;           		
            	}else{
            		self.msgCarrinho = d.mensagem;
            	}
                
            },
            function(errResponse){
            	self.msgErro = 'Erro ao listar os produtos no carrinho';
            }
        );
    }
    
    function carregarProdutosCadastrados(){
    	 ProdutoService.listarProdutos()
         .then(
	         function(d) {
	        	 if (d.codigo == 0){
	             	self.produtosCadastrados = d.resposta;
	             	self.produtosFiltrados = d.resposta;
	         	 }else{
	         		 self.msgErro = d.mensagem;
	         	 }
	         },
	         function(errResponse){
	        	 self.msgErro = 'Erro ao listar os produtos no carrinho';
	         }
         );    	
    }
    
    function filtrar(){
    	
	  if (!self.busca || self.busca === '') {
		  self.produtosFiltrados = self.produtosCadastrados;
	  }else{
		  self.produtosFiltrados = [];
		  var query = self.busca.toLowerCase();
		
		 angular.forEach(self.produtosCadastrados, function(item) {
		    if (item.nome.toLowerCase().indexOf(query) !== -1) {
		    	self.produtosFiltrados.push(item);
		      }
			}); 
	  }
    }
    
    function setProduto(produto){
    	self.produto = produto;    	
    	self.busca = produto.nome;    	
    }
    
    function adicionarProduto(){
    
    	var idProduto = self.produto.id;
    	CarrinhoService.adicionarProduto(idProduto)
        .then(
	        function(d) {
	        	if (d.codigo == 0){	        		
	        		inicializar();	        		
	        		self.msgSucesso = "Produto adicionado com sucesso!";
	        	}else{
	        		self.msgErro = d.mensagem;
	        	}
	            
	        },
	        function(errResponse){
	        	self.msgErro = 'Erro ao listar os produtos no carrinho';
	        }
        )        
    }
    
    function removerProduto(produto){      	
    	var idProduto = produto.id;
    	CarrinhoService.removerProduto(idProduto)
        .then(
	        function(d) {
	        	if (d.codigo == 0){	        		
	        		inicializar();
	        		self.msgSucesso = "Produto removido com sucesso!";
	        	}else{
	        		self.msgErro = d.mensagem;
	        	}
	            
	        },
	        function(errResponse){
	        	self.msgErro = 'Erro ao remover produto do carrinho';
	        }
        )    	
    }
    
    function atualizarProduto(produto){
    	if (produto.quantidade === parseInt(produto.quantidade, 10)){
	    	var idProduto = produto.id;
	    	CarrinhoService.atualizarProduto(produto)
	        .then(
		        function(d) {
		        	if (d.codigo == 0){	        		
		        		inicializar();
		        		self.msgSucesso = "Produto atualizado com sucesso!";
		        	}else{
		        		self.msgErro = d.mensagem;
		        	}	            
		        },
		        function(errResponse){
		        	self.msgErro = 'Erro ao atualizado produto do carrinho';
		        }
	        )  
        }else{
        	self.msgErro = "Quantidade inválida";
        }
    }
    
    function limparCarrinho(){    	
    	CarrinhoService.limparCarrinho()
        .then(
	        function(d) {
	        	if (d.codigo == 0){	        		
	        		inicializar();
	        		self.msgSucesso = "Carrinho esvaziado com sucesso!";
	        	}else{
	        		self.msgErro = d.mensagem;
	        	}	            
	        },
	        function(errResponse){
	        	self.msgErro = 'Erro ao limpar carrinho de produto';
	        }
        ) 
    }
}]);