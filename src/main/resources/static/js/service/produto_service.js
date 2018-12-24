'use strict';
 
angular.module('carrinhoApp').factory('ProdutoService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = "http://localhost:9090/produto/";
    	
    var factory = {
    		listarProdutos: listarProdutos
    };
 
    return factory;
 
    function listarProdutos() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	deferred.resolve(response.data);            	
            },
            function(errResponse){
                console.error('Erro enquanto listava todos os produtos cadastrado');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);