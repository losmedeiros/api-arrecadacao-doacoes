API de Doações
Uma API RESTful desenvolvida em Spring Boot para gerenciar usuários, beneficiários e pedidos de doação. Essa API permite que os usuários cadastrem beneficiários, gerenciem pedidos de doação e autentiquem-se para acessar funcionalidades protegidas.

Funcionalidades
Autenticação de Usuários
Cadastro de Usuários: Crie novos usuários para acessar o sistema.
Login de Usuários: Realize autenticação com email e senha. (Futuramente, suporte a JWT).
Gerenciamento de Beneficiários
Cadastro de Beneficiários: Associe beneficiários ao usuário autenticado.
Listagem de Beneficiários: Visualize todos os beneficiários cadastrados por um usuário.
Atualização de Beneficiários: Atualize informações como nome ou descrição de necessidades.
Exclusão de Beneficiários: Remova beneficiários cadastrados.
Gerenciamento de Pedidos de Doação
Criação de Pedidos de Doação: Cadastre pedidos específicos para beneficiar um usuário, como arrecadação de produtos ou valores monetários.
Listagem de Pedidos de Doação: Visualize os pedidos associados aos beneficiários do usuário autenticado.
Atualização de Pedidos de Doação: Atualize informações como descrição e valor estimado.
Exclusão de Pedidos de Doação: Remova pedidos que não são mais necessários.

Tecnologias Utilizadas
Java 17: Linguagem principal para a API.
Spring Boot 3.x: Framework para desenvolvimento rápido de aplicações.
Spring Data JPA: Gerenciamento de persistência e consultas ao banco.
Spring Security: Implementação de autenticação básica (com suporte planejado para JWT).
Jakarta Validation: Validação de dados recebidos nas requisições.
MySQL: Banco de dados relacional para armazenamento de dados.
Maven: Gerenciador de dependências e build.
Lombok: Redução de código boilerplate como getters e setters.
Postman: Testes e validação da API.
Configuração do Banco de Dados (MySQL)

Certifique-se de que o MySQL está instalado e em execução no seu ambiente.
Crie o banco de dados no MySQL:

sql

CREATE DATABASE bancodedoacoes;
Configure as credenciais no arquivo application.properties:

properties

# Configuração do banco de dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/bancodedoacoes
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configuração do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Ajuste as dependências do MySQL no pom.xml:

Certifique-se de que a dependência do MySQL está presente:

Endpoints Disponíveis

Usuários
POST /api/usuarios/cadastrar
Cadastra um novo usuário no sistema.
Exemplo de Requisição:

json
{
    "nome": "João da Silva",
    "email": "joao.silva@example.com",
    "senha": "123456"
}
POST /api/usuarios/login
Realiza o login com email e senha.

Beneficiários
POST /api/beneficiarios
Cadastra um novo beneficiário.
GET /api/beneficiarios
Lista todos os beneficiários de um usuário autenticado.
PUT /api/beneficiarios/{idBeneficiario}
Atualiza informações de um beneficiário.
DELETE /api/beneficiarios/{idBeneficiario}
Exclui um beneficiário pelo ID.

Pedidos de Doação
POST /api/doacoes
Cadastra um novo pedido de doação.
GET /api/doacoes
Lista todos os pedidos de doação associados ao usuário autenticado.
PUT /api/doacoes/{idDoacao}
Atualiza informações de um pedido de doação.
DELETE /api/doacoes/{idDoacao}
Exclui um pedido de doação pelo ID.

Como Rodar o Projeto
Pré-requisitos
Java 17 ou superior instalado.
MySQL configurado com o banco bancodedoacoes.
Maven instalado.
Passos para rodar o projeto
Clone o repositório:

Melhorias Futuras
Implementação de autenticação com JWT.
Gerenciamento de permissões por perfis (admin e usuário comum).
Documentação automatizada com Swagger.
Integração com outros sistemas de pagamento para pedidos de doação.
