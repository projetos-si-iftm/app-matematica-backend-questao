# App Ensino Matemática Backend Questão

Este é o backend de um aplicativo de ensino de matemática feito para a comunidade externa, responsável por gerenciar questões, alternativas e categorias. 

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Documentação MongoDB (NoSQL)] 
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Instalação e Execução](#instalação-e-execução)
- [Endpoints](#endpoints)
- [Licença](#licença)

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.0
- MongoDB
- Lombok
- Maven

## Documentação MongoDB (NoSQL)

```bash
{
  "id_questao": UUID,
  "titulo": String,
  "enunciado": String,
  "imagem": String,
  "dificuldade": int,
  "alternativa": [
    {
      "resposta": String,
      "correta": Boolean
    }
  ],
  "categoria": [
    {
      "id_categoria": UUID,
      "nome": String
    }
  ]
}
```

Exemplo de questão:
![Flowchart (5)](https://github.com/user-attachments/assets/e3f9e27a-1210-4a2a-ba85-6b5c53862fea)


### Por que utilizar MongoDB (banco NOSQL)?
- MongoDB oferece uma abordagem de consulta direta, usando filtros baseados em JSON ou BSON. Isso significa que você pode fazer buscas de maneira simples, sem a complexidade das joins de bancos SQL.
- No MongoDB, você pode armazenar dados sem se preocupar com a criação de muitas tabelas ou coleções adicionais, como ocorre em bancos relacionais para normalização. A estrutura de dados no MongoDB (documentos JSON) já permite agrupar informações relacionadas em um único documento, sem a necessidade de muitas referências ou tabelas extras.
- MongoDB é altamente flexível quando se trata de salvar dados. Não há necessidade de seguir um esquema fixo. Se você quiser adicionar novos campos ou novos tipos de dados (por exemplo, um novo tipo de pergunta ou uma nova categoria), você pode fazer isso sem grandes mudanças ou migrações no banco de dados. Isso permite que você se adapte rapidamente a novos requisitos ou alterações no modelo de dadoS.
- MongoDB é projetado para otimizar o desempenho em grandes volumes de dados, especialmente quando se trata de leitura e gravação rápidas. Ele oferece recursos como índices para acelerar as buscas, o que é essencial em sistemas que lidam com muitos documentos, como um banco de questões.
-  Otimização da performance da busca de dados presentes no banco, pelo fato de não haver necessidade de uma coleção com base na classe "Alternativa", e consequentemente um novo id, aumentando a velocidade da resposta.

## Estrutura do projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── br/
│   │       └── edu/
│   │           └── iftm/
│   │               └── app_ensino_matematica_backend_questao/
│   │                   ├── config/
│   │                   │   └── WebConfig.java
│   │                   ├── controller/
│   │                   │   └── QuestaoController.java
│   │                   │   └── CategoriaController.java
│   │                   ├── model/
│   │                   │   ├── Alternativa.java
│   │                   │   ├── Categoria.java
│   │                   │   ├── Questao.java
│   │                   │   └── DTO/
│   │                   │       └── CategoriaDTO.java
│   │                   │       └── QuestaoDTO.java
│   │                   ├── repository/
│   │                   │      └── CategoriaRepository.java
│   │                   │      └── QuestaoRepository.java
│   │                   ├── service/
│   │                   │      └── CategoriaService.java
│   │                   │      └── QuestaoService.java
│   │                   └── AppEnsinoMatematicaBackendQuestaoApplication.java
│   └── resources/
│       ├── application.yml
│       
│           
└── test/
    └── java/
        └── br/
            └── edu/
                └── iftm/
                    └── app_ensino_matematica_backend_questao/
                        └── AppEnsinoMatematicaBackendQuestaoApplicationTests.java
```

## Configuração do Ambiente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- MongoDB

### Instalação e Execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/app_ensino_matematica_backend_questao.git
   cd app_ensino_matematica_backend_questao

2. Instale as dependências:
   ```bash
   mvn install
   
3. Configuração do MongoDB
Certifique-se de que o MongoDB esteja em execução e configurado corretamente. Você pode ajustar as configurações de conexão no arquivo `application.properties`.

4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   
A aplicação estará disponível em `http://localhost:8080`.

# Endpoints

## Categoria

### GET /manage/category

- **Descrição**: Retorna todas as categorias.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    [
      {
        "id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557",
        "nome": "Álgebra"
      },
      {
        "id_categoria": "2c8f2350-7530-4918-a05b-32b4da555558",
        "nome": "Geometria"
      }
    ]
    ```

### GET /manage/category/{id_categoria}

- **Descrição**: Retorna uma categoria pelo ID.
- **Parâmetros**:
  - `id_categoria` (UUID): ID da categoria.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    {
      "id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557",
      "nome": "Álgebra"
    }
    ```

### POST /manage/category

- **Descrição**: Cria uma nova categoria.
- **Corpo da Requisição**:
  - `CategoriaDTO`: Objeto contendo os dados da categoria.
  - Exemplo de Requisição:
    ```json
    {
      "nome": "Trigonometria"
    }
    ```
- **Resposta**:
  - Status: 201 Created
  - Corpo da Resposta:
    ```json
    {
      "id_categoria": "3d9f2350-7530-4918-a05b-32b4da555559",
      "nome": "Trigonometria"
    }
    ```

### DELETE /manage/category/{id_categoria}

- **Descrição**: Deleta uma categoria pelo ID.
- **Parâmetros**:
  - `id_categoria` (UUID): ID da categoria.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    {
      "id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557",
      "nome": "Álgebra"
    }
    ```

### PATCH /manage/category/{id_categoria}

- **Descrição**: Atualiza uma categoria pelo ID.
- **Parâmetros**:
  - `id_categoria` (UUID): ID da categoria.
- **Corpo da Requisição**:
  - `CategoriaDTO` : Objeto contendo os dados atualizados da categoria.
  - Exemplo de Requisição:
    ```json
    {
      "nome": "Álgebra Avançada"
    }
    ```
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    {
      "id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557",
      "nome": "Álgebra Avançada"
    }
    ```
    ## Questao

### POST /manage/question

- **Descrição**: Cria uma nova questão.
- **Corpo da Requisição**:
  - `QuestaoDTO`: Objeto contendo os dados da questão.
  - Exemplo de Requisição:
    ```json
    {
      "titulo": "Qual é a raiz quadrada de 16?",
      "enunciado": "Calcule a raiz quadrada de 16.",
      "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
      "dificuldade": 1,
      "alternativa": [
        {"resposta": "2", "correta": false},
        {"resposta": "4", "correta": true},
        {"resposta": "8", "correta": false}
      ],
      "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557"}]
    }
    ```
- **Resposta**:
  - Status: 201 Created
  - Corpo da Resposta:
    ```json
    {
      "id_questao": "3d9f2350-7530-4918-a05b-32b4da555559",
      "titulo": "Qual é a raiz quadrada de 16?",
      "enunciado": "Calcule a raiz quadrada de 16.",
      "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
      "dificuldade": 1,
      "alternativa": [
        {"resposta": "2", "correta": false},
        {"resposta": "4", "correta": true},
        {"resposta": "8", "correta": false}
      ],
      "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557", "nome": "Matemática"}]
    }
    ```

### GET /manage/question/{id_questao}

- **Descrição**: Retorna uma questão pelo ID.
- **Parâmetros**:
  - `id_questao` (UUID): ID da questão.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    {
      "id_questao": "3d9f2350-7530-4918-a05b-32b4da555559",
      "titulo": "Qual é a raiz quadrada de 16?",
      "enunciado": "Calcule a raiz quadrada de 16.",
      "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
      "dificuldade": 1,
      "alternativa": [
        {"resposta": "2", "correta": false},
        {"resposta": "4", "correta": true},
        {"resposta": "8", "correta": false}
      ],
      "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557", "nome": "Matemática"}]
    }
    ```

### GET /manage/question

- **Descrição**: Retorna todas as questões.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    [
      {
        "id_questao": "3d9f2350-7530-4918-a05b-32b4da555559",
        "titulo": "Qual é a raiz quadrada de 16?",
        "enunciado": "Calcule a raiz quadrada de 16.",
        "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
        "dificuldade": 1,
        "alternativa": [
          {"resposta": "2", "correta": false},
          {"resposta": "4", "correta": true},
          {"resposta": "8", "correta": false}
        ],
        "resposta_correta": "4",
        "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557", "nome": "Matemática"}]
      }
    ]
    ```

### GET /manage/question/category/{id_categoria}

- **Descrição**: Retorna questões por categoria.
- **Parâmetros**:
  - `id_categoria` (UUID): ID da categoria.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    [
      {
        "id_questao": "3d9f2350-7530-4918-a05b-32b4da555559",
        "titulo": "Qual é a raiz quadrada de 16?",
        "enunciado": "Calcule a raiz quadrada de 16.",
        "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
        "dificuldade": 1,
        "alternativa": [
          {"resposta": "2", "correta": false},
          {"resposta": "4", "correta": true},
          {"resposta": "8", "correta": false}
        ],
        "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557", "nome": "Matemática"}]
      }
    ]
    ```

### GET /manage/question/category/dificuldade

- **Descrição**: Retorna questões por categoria e dificuldade.
- **Parâmetros**:
  - `id_categoria` (UUID): ID da categoria.
  - `dificuldade` (int): Nível de dificuldade da questão.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    [
      {
        "id_questao": "3d9f2350-7530-4918-a05b-32b4da555559",
        "titulo": "Qual é a raiz quadrada de 16?",
        "enunciado": "Calcule a raiz quadrada de 16.",
        "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
        "dificuldade": 1,
        "alternativa": [
          {"resposta": "2", "correta": false},
          {"resposta": "4", "correta": true},
          {"resposta": "8", "correta": false}
        ],
        "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557", "nome": "Matemática"}]
      }
    ]
    ```

### GET /manage/question/category/dificuldade/random

- **Descrição**: Retorna questões aleatórias por categoria e dificuldade.
- **Parâmetros**:
  - `id_categoria` (UUID): ID da categoria.
  - `dificuldade` (int): Nível de dificuldade da questão.
- **Resposta**:
  - Status: 200 OK
  - Corpo da Resposta:
    ```json
    [
      {
        "id_questao": "3d9f2350-7530-4918-a05b-32b4da555559",
        "titulo": "Qual é a raiz quadrada de 16?",
        "enunciado": "Calcule a raiz quadrada de 16.",
        "imagem": "https://example.com/imagens/raiz-quadrada-16.jpg",
        "dificuldade": 1,
        "alternativa": [
          {"resposta": "2", "correta": false},
          {"resposta": "4", "correta": true},
          {"resposta": "8", "correta": false}
        ],
        "categoria": [{"id_categoria": "1b7f2350-7530-4918-a05b-32b4da555557", "nome": "Matemática"}]
      }
    ]
    ```

    ## Licença

    Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.


