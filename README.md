# App Ensino Matemática Backend Questão

Este é o backend de um aplicativo de ensino de matemática feito para a comunidade externa. Ele é responsável por gerenciar questões, alternativas e categorias. 

O sistema é composto por múltiplos microsserviços que se complementam, incluindo os serviços de 👉 [resposta](https://github.com/projetos-si-iftm/app-matematica-backend-resposta) e 👉 [usuários](https://github.com/projetos-si-iftm/app-matematica-backend), responsáveis respectivamente pelo gerenciamento do banco de respostas e gerenciamento do ranking dos alunos, e pelo gerenciamento das informações dos alunos, professores e turma.

- [Repositório do Aplicativo Socratic - Aluno](https://github.com/projetos-si-iftm/app-matematica-frontend)
  
- [Repositório da Interface Web - Professor](https://github.com/projetos-si-iftm/app-matematica-frontend-professor)

## Sumário

- [Contribuidores](#contribuidores)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Endpoints](#endpoints)
- [Licença](#licença)

# Contribuidores

- [@fromanoel](https://github.com/fromanoel) – Backend: desenvolvimento e integração dos microsserviços, configuração do Gateway e do Eureka Server, e organização da documentação com Swagger.
- [@madu-silva](https://github.com/madu-silva) – Backend: integração com Firebase, geração do token JWT.
- [IsabelaQM](https://github.com/IsabelaQM) – Frontend: desenvolvimento da aplicação em React e React Native.
- [@samylledutra](https://github.com/samylledutra) – Frontend: desenvolvimento da aplicação em React e React Native.
- [@stclaire1](https://github.com/stclaire1) – Frontend: desenvolvimento da aplicação em React e React Native.

# Tecnologias Utilizadas

- Java 17
- Spring Boot 3.0
- Spring Data MongoDB
- Lombok

## Microsserviços e Infraestrutura
- Spring Cloud Gateway
- Eureka Server

## Biblioteca Compartilhada DTO's

Este projeto utiliza uma biblioteca compartilhada de DTOs desenvolvida especialmente para padronizar a comunicação entre os microsserviços do sistema.
Essa biblioteca contém as classes de transferência de dados (DTOs) utilizadas por todos os serviços — como usuário, questão e resposta — garantindo consistência nas trocas de informações e facilitando a manutenção.

👉 [Repositório da biblioteca de DTO's](https://github.com/fromanoel/app-matematica-dtos)

## Documentação 
- Swagger (OpenAPI)

## Banco de Dados
- MongoDB

### Por que utilizar MongoDB (banco NOSQL)?
- MongoDB oferece uma abordagem de consulta direta, usando filtros baseados em JSON ou BSON. Isso significa que você pode fazer buscas de maneira simples, sem a complexidade das joins de bancos SQL.
- No MongoDB, você pode armazenar dados sem se preocupar com a criação de muitas tabelas ou coleções adicionais, como ocorre em bancos relacionais para normalização. A estrutura de dados no MongoDB (documentos JSON) já permite agrupar informações relacionadas em um único documento, sem a necessidade de muitas referências ou tabelas extras.
- MongoDB é altamente flexível quando se trata de salvar dados. Não há necessidade de seguir um esquema fixo. Se você quiser adicionar novos campos ou novos tipos de dados (por exemplo, um novo tipo de pergunta ou uma nova categoria), você pode fazer isso sem grandes mudanças ou migrações no banco de dados. Isso permite que você se adapte rapidamente a novos requisitos ou alterações no modelo de dadoS.
- MongoDB é projetado para otimizar o desempenho em grandes volumes de dados, especialmente quando se trata de leitura e gravação rápidas. Ele oferece recursos como índices para acelerar as buscas, o que é essencial em sistemas que lidam com muitos documentos, como um banco de questões.
-  Otimização da performance da busca de dados presentes no banco, pelo fato de não haver necessidade de uma coleção com base na classe "Alternativa", e consequentemente um novo id, aumentando a velocidade da resposta.

## Estrutura do projeto

O projeto está organizado nas seguintes pastas:

- `controller`: Contém os controladores REST que expõem os endpoints da API.
- `model`: Contém as classes de modelo que representam os dados.
- `repository`: Contém as interfaces de repositório para acesso ao banco de dados.
- `service`: Contém as classes de serviço que implementam a lógica de negócios.
- `converter`: Contém a lógica de conversão dos DTO's para Models, e vice-versa.
- `config`: Contém a configuração da documentação do Swagger (OpenAPI).


```bash
+---main
|   +---java
|   |   \---br
|   |       \---edu
|   |           \---iftm
|   |               \---app_ensino_matematica_backend_questao
|   |                   |   AppEnsinoMatematicaBackendQuestaoApplication.java
|   |                   |   
|   |                   +---config
|   |                   |       SwaggerConfig.java
|   |                   |       
|   |                   +---controller
|   |                   |       CategoriaController.java
|   |                   |       ImagemController.java
|   |                   |       QuestaoController.java
|   |                   |       
|   |                   +---converter
|   |                   |       CategoriaConverter.java
|   |                   |       DTOConverter.java
|   |                   |       
|   |                   +---model
|   |                   |       Alternativa.java
|   |                   |       Categoria.java
|   |                   |       Questao.java
|   |                   |       
|   |                   +---repository
|   |                   |       AlternativaRepository.java
|   |                   |       CategoriaRepository.java
|   |                   |       QuestaoRepository.java
|   |                   |
|   |                   \---service
|   |                           CategoriaService.java
|   |                           QuestaoService.java
|   |                           UrlBuilderService.java
|   |
|   \---resources
|           application.yml
```

# Configuração do Ambiente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- MongoDB

## Instalação e Execução

### Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/app_ensino_matematica_backend_questao.git
   cd app_ensino_matematica_backend_questao
```

### Configuração do MongoDB
Certifique-se de que o MongoDB esteja em execução e configurado corretamente. Você pode ajustar as configurações de conexão no arquivo `application.yml`.
 
### Compilação e Execução

Antes de iniciar este serviço, certifique-se de que os seguintes projetos estejam rodando:

[Eureka Server](https://github.com/projetos-si-iftm/app-matematica-eureka) - responsável pelo service discovery

[Gateway](https://github.com/projetos-si-iftm/app-matematica-backend-gateway) - responsável pelo roteamento das requisições

Para compilar e executar o projeto, use os seguintes comandos:

```bash
mvn clean install
mvn spring-boot:run
```

# Endpoints

A documentação completa está disponível via Swagger
👉 [Acesse a documentação Swagger aqui](https://app-matematica-backend-questao-3a364d6ca0e2.herokuapp.com/swagger-ui/index.html)

# Licença

Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.


