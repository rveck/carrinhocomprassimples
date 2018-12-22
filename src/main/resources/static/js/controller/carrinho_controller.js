'use strict';
 
angular.module('carrinhoApp').controller('CarrinhoController', ['$scope', 'CarrinhoService', 'ProdutoService', 
	function($scope, CarrinhoService, ProdutoService) {
    
	var self = this;
	
	self.submit = submit;
	self.filtrar = filtrar;
	self.setProduto = setProduto;
	self.adicionarProduto = adicionarProduto;
	self.removerProduto = removerProduto;
	
	self.produto = {
			id : null,
			nome : '',
			valor : 0,
			quantidade : 0
	};
    self.carrinho = [];
    self.produtosCadastrados = [];
    self.produtosFiltrados = [];
    self.focus = false;
    self.busca = '';
 
    listarCarrinho();
    carregarProdutosCadastrados();
 
    function listarCarrinho(){
        CarrinhoService.listarCarrinho()
            .then(
            function(d) {
                self.carrinho = d;
            },
            function(errResponse){
                console.error('Erro ao listar os produtos no carrinho');
            }
        );
    }
    
    function carregarProdutosCadastrados(){
    	 ProdutoService.listarProdutos()
         .then(
         function(d) {
             self.produtosCadastrados = d;
             self.produtosFiltrados = d;
         },
         function(errResponse){
             console.error('Erro ao listar os produtos no carrinho');
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
    	console.log("Produto selecionado: " + produto.nome);
    }
    
    function adicionarProduto(){
    	self.carrinho.push(self.produto);
    	console.log("Produto adicionado ao carrinho: " + produto.nome);
    }
    
    function removerProduto(produto){      	
    	for(var i = 0; i < self.carrinho.length; i++) {    		
    	    if(self.carrinho[i].id === produto.id) {
    	    	console.log("Produto removido do carrinho: " + self.carrinho[i].id);
    	    	self.carrinho.splice(i, 1);    	    	
    	    	break;
    	    }
    	}    	
    }
    
    function submit() {
        if(self.produto.id===null){
            console.log('Saving New User', self.produto);            
        }else{            
            console.log('Produto com ID atualizado ', self.produto.id);
        }
        
    }
}]);