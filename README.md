# Carrinho de Compras Simples
O objetivo dessa aplicação é permitir a gestão de um carrinho fake de compras salvo na sessão a partir de produtos cadastrados na base de dados.

# Especificações técnicas:
- [x] Spring Boot
- [x] Spring MVC
- [x] Spring Data
- [x] Spring Security
- [x] Spring Test
- [x] MySQL DB
- [x] Angular
- [x] Thymeleaf
- [x] JQuery
- [x] CSS
- [x] Bootstrap
- [x] HTML 5
- [x] Maven
- [x] GIT

# Gestão do carrinho através da interface web:
- URL: http://35.211.193.141:9090/
- Usuário: user
- Senha: secret123

<img width="1144" alt="screen shot 2018-12-24 at 20 12 40" src="https://user-images.githubusercontent.com/10779649/50407168-f0646900-07b8-11e9-9a13-b50de31fdf67.png">

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
# Roteiro básico de instalação
1. Criar o schema "db_carrinho" na base de dados;
2. Definir um usuário com acesso de escrita e consulta à base;
3. Baixar código fonte;
4. Executar o script sql que se encontra no diretó sql;
5. Editar o arquivo pom.xml atualizando as configurações da base de dados e log do profile dev;
6. Executar o seguinte comando onde se encontra o arquivo pom.xml:
```
mvn clean install
```
6. Executar o seguinte comando para inicializar a aplicação:
```
java -jar -Xmx512m -Dfile.encoding=UTF-8 -Duser.timezone=America/Sao_Paulo -Duser.language=pt -Duser.region=BR target/carrinho-0.0.1-SNAPSHOT.jar &
```
