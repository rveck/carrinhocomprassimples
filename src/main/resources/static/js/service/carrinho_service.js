'use strict';
 
angular.module('carrinhoApp').factory('CarrinhoService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = "http://localhost:8080/carrinho/";
    	
    var factory = {
    		listarCarrinho: listarCarrinho
    };
 
    return factory;
 
    function listarCarrinho() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	if (response.data.codigo == 0){
            		deferred.resolve(response.data.resposta);
            	}
            },
            function(errResponse){
                console.error('Erro enquanto listava todos os produtos do carrinho');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);