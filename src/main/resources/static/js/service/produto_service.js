'use strict';
 
angular.module('carrinhoApp').factory('ProdutoService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = "http://localhost:8080/produto/";
    	
    var factory = {
    		listarProdutos: listarProdutos
    };
 
    return factory;
 
    function listarProdutos() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	if (response.data.codigo == 0){
            		deferred.resolve(response.data.resposta);
            	}
            },
            function(errResponse){
                console.error('Erro enquanto listava todos os produtos cadastrado');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);