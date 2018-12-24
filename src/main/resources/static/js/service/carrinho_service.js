'use strict';
 
angular.module('carrinhoApp').factory('CarrinhoService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = serverUrl + ":" + serverPort + "/carrinho/";
    var REST_SERVICE_URI_LIMPAR_CARRINHO = REST_SERVICE_URI + "limpar";
    	
    var factory = {
    		listarCarrinho: listarCarrinho,
    		adicionarProduto: adicionarProduto,
    		removerProduto: removerProduto,
    		atualizarProduto: atualizarProduto,
    		limparCarrinho: limparCarrinho,
    };
 
    return factory;
 
    function listarCarrinho() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {            	
            	deferred.resolve(response.data);            	
            },
            function(errResponse){
                console.error('Erro enquanto listava todos os produtos do carrinho');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function adicionarProduto(idProduto) {
    	var deferred = $q.defer();
    	var data = JSON.stringify({id:idProduto});
        $http.post(REST_SERVICE_URI,data)
            .then(
            function (response) {            	
            	deferred.resolve(response.data);            	
            },
            function(errResponse){
                console.error('Erro enquanto adicionava produto ao carrinho');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
        
    }
    
    function removerProduto(idProduto){ 
        var deferred = $q.defer();
    	var url = REST_SERVICE_URI + idProduto;
    	$http.delete(url)
        .then(
	        function (response) {            	
	        	deferred.resolve(response.data);            	
	        },
	        function(errResponse){
	            console.error('Erro enquanto removia produto do carrinho');
	            deferred.reject(errResponse);
	        }
        );
    	return deferred.promise;
    }
    
    function atualizarProduto(produto){
    	var deferred = $q.defer();
    	var data = JSON.stringify(produto);
    	var url = REST_SERVICE_URI + produto.id;
        $http.put(url,data)
            .then(
            function (response) {            	
            	deferred.resolve(response.data);            	
            },
            function(errResponse){
                console.error('Erro enquanto atualizava produto do carrinho');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    	
    }
    
    function limparCarrinho(){    
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_LIMPAR_CARRINHO)
            .then(
            function (response) {            	
            	deferred.resolve(response.data);             	
            },
            function(errResponse){
                console.error('Erro enquanto limpava o carrinho');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;    	    	
    }
  
}]);