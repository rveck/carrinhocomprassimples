# Carrinho de Compras Simples
O objetivo dessa aplicação é permitir a gestão de um carrinho fake de compras salvo na sessão a partir de produtos cadastrados na base de dados.

# Especificações técnicas:
- [x] Spring Boot
- [x] Spring Data
- [x] Spring Test
- [x] MySQL DB
- [x] Angular
- [x] Thymeleaf
- [x] JQuery
- [x] Bootstrap
- [x] HTML 5
- [x] Maven

# Gestão do carrinho através da interface web:
- URL: http://35.211.193.141:9090/
- Usuário: user
- Senha: secret123

# Gestão do carrinho através da API Rest:
**1. Listar itens no carrinho:**
- URL: http://35.211.193.141:9090/carrinho
- Método: GET

**2. Adicionar produto no carrinho:** 
- URL: http://35.211.193.141:9090/carrinho
- Método: POST
- Exemplo request:
```json
{
  "id":<ID_DO_PRODUTO>
}
```
  
**3. Remover produto do carrinho:** 
- URL: http://35.211.193.141:9090/carrinho/<ID_DO_PRODUTO>
- Método: DELETE

**4. Atualizar quantidade do produto no carrinho:** 
- URL: http://35.211.193.141:9090/carrinho/<ID_DO_PRODUTO>
- Método: PUT
- Exemplo request:
```json
{
	"quantidade":<NOVA_QUANTIDADE>
}
```

**5. Esvaziar carrinho:** 
- URL: http://35.211.193.141:9090/carrinho/limpar
- Método: GET

# API Rest de produtos
**1. Listar produtos cadastrados:**
- URL: http://35.211.193.141:9090/produto
- Método: GET

**2. Cadastrar novo produto:** 
- URL: http://35.211.193.141:9090/produto
- Método: POST
- Exemplo request:
```json
{
	"nome":"Bicicleta Caloi Aro 18",
	"valor": 800
}
```
  
**3. Remover produto:** 
- URL: http://35.211.193.141:9090/produto/<ID_DO_PRODUTO>
- Método: DELETE

**4. Atualizar dados do produto:** 
- URL: http://35.211.193.141:9090/produto/<ID_DO_PRODUTO>
- Método: PUT
- Exemplo request:
```json
{
	"nome":"Bicicleta Aro 18",
	"valor": 900
}
```
