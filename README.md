# App Ensino Matemática Backend Questão

Este é o backend de um aplicativo de ensino de matemática feito para a comunidade externa, responsável por gerenciar questões, alternativas e categorias. 

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
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


