# PROJETO SALES CONTROL

## Sistema de Gerenciamento de Vendas em Java com Spring Boot

---

Este projeto apresenta uma aplicação backend robusta e escalável, desenvolvida em Java com Spring Boot, focada no gerenciamento eficiente de vendas. O Sales Control foi projetado com uma arquitetura em camadas, garantindo alta manutenibilidade, testabilidade e separação clara de responsabilidades. É uma solução completa para registrar, consultar e gerenciar clientes e pedidos, com funcionalidades avançadas e aderência às melhores práticas de desenvolvimento de software.

### 🚀 **Visão Geral**  

O Sales Control é um sistema backend que oferece uma API RESTful para a gestão de vendas. Ele foi construído para ser a espinha dorsal de qualquer aplicação que necessite de um controle rigoroso sobre clientes e transações de vendas. A aplicação adota uma arquitetura em camadas bem definida, promovendo um código limpo, modular e fácil de estender.

---

### 🛠️ **Tecnologias Utilizadas**

Este projeto demonstra proficiência em um conjunto abrangente de tecnologias modernas para desenvolvimento backend:

Java 21: ☕ A versão mais recente da linguagem, aproveitando seus recursos e melhorias de performance.

Spring Boot 3: 🍃 Framework líder para construção de aplicações Java, agilizando o desenvolvimento e a configuração.

Spring Data JPA: 💾 Simplifica a interação com o banco de dados, fornecendo uma camada de abstração poderosa sobre o Hibernate.

Spring Security: 🔒 Framework robusto para autenticação e autorização, garantindo a segurança da API.

MySQL: 🐬 Banco de dados relacional amplamente utilizado, escolhido pela sua confiabilidade e performance.

Maven: 📦 Ferramenta de automação de build e gerenciamento de dependências.

Docker: 🐳 Para conteinerização da aplicação e do banco de dados, facilitando o deploy e a portabilidade.

JUnit 5: 🧪 Framework de testes unitários para garantir a qualidade e a robustez do código.

MapStruct: 🗺️ Gerador de código para mapeamento de objetos, reduzindo boilerplate e erros manuais.

Swagger/OpenAPI: 📄 Para documentação automática da API, facilitando o consumo e a compreensão dos endpoints.

H2 Database: 🗄️ Banco de dados em memória utilizado para testes, garantindo isolamento e rapidez.

Lombok: 🧩 Reduz o código boilerplate (getters, setters, construtores) através de anotações.

Reflection: 💡 Utilizado para implementar atualizações dinâmicas e parciais de recursos.

AOP (Aspect-Oriented Programming): 📊 Implementado para logging automático e tratamento de preocupações transversais.

---

### 🏛️ **Arquitetura**

O projeto segue um padrão de arquitetura em camadas (Layered Architecture), que é fundamental para a organização e escalabilidade de aplicações empresariais:

Controller: Responsável por receber as requisições HTTP, validar os dados de entrada e delegar a lógica de negócio para a camada de serviço.

Service: Contém a lógica de negócio principal da aplicação, orquestrando as operações e interagindo com a camada de repositório.

Repository: Abstrai a interação com o banco de dados, fornecendo métodos para operações CRUD (Create, Read, Update, Delete).

DTOs (Data Transfer Objects): Utilizados para transferir dados entre as camadas, garantindo que apenas as informações necessárias sejam expostas e manipuladas.

Entidades: Representam a estrutura dos dados no banco de dados, mapeadas via JPA.

Separação de Responsabilidades: Cada camada possui uma responsabilidade única e bem definida, minimizando acoplamento e maximizando coesão.

Testes Abrangentes: A arquitetura facilita a escrita de testes unitários e de integração em todas as camadas (Repository, Service, Controller), garantindo a confiabilidade do sistema.

---

### ✨ **Funcionalidades Principais**  

O Sales Control oferece um conjunto robusto de funcionalidades para a gestão de vendas:

✅ Cadastro e Gerenciamento de Clientes: CRUD completo para informações de clientes.  
✅ Registro e Consulta de Pedidos: Gerenciamento detalhado de pedidos de vendas.  
✅ Busca Avançada: Capacidade de buscar clientes por nome, CPF ou apelido.  
✅ Integração com Endereços: Busca automática de endereços via CEP para agilizar o cadastro.  
✅ Relatórios de Vendas: Geração de relatórios para análise de performance.  
✅ Paginação de Resultados: Otimização da consulta de grandes volumes de dados.  
✅ Autenticação e Autorização: Controle de acesso baseado em roles e JWT.  
✅ Documentação Automática com Swagger: API auto-documentada e interativa.  
✅ Testes Unitários Abrangentes: Alta cobertura de testes para garantir a qualidade do código.

---

### 📂 **Estrutura do Projeto**

A organização do projeto segue uma estrutura de pacotes lógica e intuitiva, facilitando a navegação e a manutenção:

src/main/java/com/salescontrol/salescontrol

├── config/                  # Configurações de segurança, AOP, etc.  
├── controller/              # Endpoints RESTful da API  
├── dto/                     # Data Transfer Objects (DTOs)  
│   ├── request/             # DTOs para requisições  
│   └── response/            # DTOs para respostas  
├── entity/                  # Entidades JPA (modelos de dados)  
├── exception/               # Classes de exceção personalizadas  
├── mapper/                  # Interfaces MapStruct para mapeamento de DTOs  
├── repository/              # Interfaces Spring Data JPA para acesso ao BD  
├── service/                 # Lógica de negócio principal  
├── util/                    # Classes utilitárias e helpers  
└── SalescontrolApplication.java # Classe principal da aplicação

---

### ⚙️ **Como Executar o Projeto**

Para rodar o Sales Control em sua máquina local, siga os passos abaixo:

Pré-requisitos

- Java Development Kit (JDK) 21 ou superior  
- Apache Maven 3.6.x ou superior  
- MySQL Server 8.0 ou superior (ou Docker para rodar o MySQL em container)

--

Passos para Clonar e Executar

1. **Clone o repositório:**  
git clone https://github.com/matheualan/sales-control.git  
cd sales-control
    
2.  **Configuração do Banco de Dados MySQL:**  
Crie um banco de dados MySQL chamado `sales_control_db`.  
Atualize as credenciais do banco de dados no arquivo `src/main/resources/application-dev.properties` (ou `application.properties`):

        spring.datasource.url=jdbc:mysql://localhost:3306/sales_control_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
        spring.datasource.username=seu_usuario_mysql
        spring.datasource.password=sua_senha_mysql

3. **Execute a aplicação com Maven:**  
    mvn spring-boot:run  
    A aplicação estará disponível em `http://localhost:8080`.  

--

Rodando com Docker

Para uma execução mais simplificada e isolada, você pode usar Docker e Docker Compose:

1. **Certifique-se de ter Docker e Docker Compose instalados.**

2. **Construa a imagem Docker da aplicação:**  
    mvn clean package -DskipTests  
    docker build -t sales-control

3. **Inicie os serviços (aplicação e MySQL) com Docker Compose:**  
    docker-compose up -d  
A aplicação estará disponível em `http://localhost:8080` e o MySQL em `localhost:3306`.
    
---
    
### 🌍 **Configuração de Ambientes**

O projeto suporta diferentes perfis de ambiente para desenvolvimento, produção e testes, configurados através dos arquivos application-{profile}.properties:

- application-dev.properties: Configurações para ambiente de desenvolvimento (ex: H2 Console habilitado, logs detalhados).

- application-prod.properties: Configurações para ambiente de produção (ex: segurança reforçada, otimizações de performance).

- application-test.properties: Configurações específicas para execução de testes (ex: H2 Database em memória).

Você pode ativar um perfil específico ao iniciar a aplicação, por exemplo:  
**java -jar target/sales-control.jar --spring.profiles.active=prod**

---

### 🔗 **Endpoints Principais da API**

A API RESTful oferece os seguintes endpoints principais (exemplos):

POST /api/v1/auth/register - Registrar novo usuário  
POST /api/v1/auth/authenticate - Autenticar usuário e obter JWT  
GET /api/v1/clientes - Listar todos os clientes (requer autenticação)  
GET /api/v1/clientes/{id} - Obter cliente por ID  
POST /api/v1/clientes - Cadastrar novo cliente  
PUT /api/v1/clientes/{id} - Atualizar cliente existente  
DELETE /api/v1/clientes/{id} - Excluir cliente  
GET /api/v1/pedidos - Listar todos os pedidos  
GET /api/v1/pedidos/{id} - Obter pedido por ID  
POST /api/v1/pedidos - Registrar novo pedido  

A documentação completa da API pode ser acessada via Swagger UI em http://localhost:8080/swagger-ui.html após iniciar a aplicação.

---

### 🧪 **Testes**

A qualidade do código é uma prioridade, e o projeto conta com uma suíte de testes abrangente:  

- Cobertura de Testes: Testes unitários e de integração cobrem as camadas de Repository, Service e Controller.

- JUnit 5 e Mockito: Utilizados para escrever testes robustos e isolados.  

- Testes de Integração: Verificam a interação entre as camadas e com o banco de dados (utilizando H2 em memória para agilidade).  

- Testes de Segurança: Validação dos mecanismos de autenticação e autorização.  

Para executar todos os testes:  
    mvn test
    
---

### 🌟 Diferenciais do Projeto

Este projeto se destaca por incorporar diversas boas práticas e técnicas avançadas:

*   **AOP para Logging Automático**: Implementação de Aspect-Oriented Programming para interceptar chamadas de métodos e registrar logs de forma não intrusiva, melhorando a observabilidade.<br/>
*   **Reflection para Updates Dinâmicos**: Utilização de Reflection para permitir atualizações parciais de recursos (PATCH), tornando a API mais flexível e eficiente.<br/>
*   **MapStruct para Mapeamento de DTOs**: Geração automática de código para mapeamento entre entidades e DTOs, eliminando boilerplate e garantindo consistência.<br/>
*   **Configuração Multi-Ambiente**: Suporte a perfis de ambiente (`dev`, `prod`, `test`), facilitando o gerenciamento de configurações específicas para cada contexto.<br/>
*   **Documentação Swagger Completa**: API totalmente documentada e interativa, que acelera o desenvolvimento frontend e a integração com outros sistemas.<br/>
*   **Testes em Todas as Camadas**: Uma cultura de testes robusta, com cobertura em todas as camadas da aplicação, garantindo a estabilidade e a confiabilidade do sistema.

### 📈 Próximos Passos / Melhorias Futuras

O projeto Sales Control está em constante evolução. Algumas melhorias e funcionalidades planejadas incluem:

*   **Cache com Redis**: Implementação de cache para otimizar a performance de consultas frequentes.<br/>
*   **Mensageria com RabbitMQ**: Integração com um sistema de mensageria para processamento assíncrono e desacoplamento de serviços.<br/>
*   **Microserviços**: Refatoração para uma arquitetura de microserviços, visando maior escalabilidade e resiliência.<br/>
*   **CI/CD com GitHub Actions**: Configuração de pipelines de Integração Contínua e Entrega Contínua para automatizar o build, teste e deploy.

### 👤 Autor e Contato

**Matheus Alan**
GitHub: [matheualan](https://github.com/matheualan)
