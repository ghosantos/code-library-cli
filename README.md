# 📚 CodeLibrary CLI

Um sistema de gerenciamento de livraria via Linha de Comando (CLI), desenvolvido em Java com integração a banco de dados relacional.

Este projeto foi construído com foco na aplicação de boas práticas de Programação Orientada a Objetos (POO), Arquitetura em Camadas e manipulação segura de dados via JDBC.

## ✨ Funcionalidades

O sistema permite o gerenciamento completo do acervo de livros através de um menu interativo:
- **Adicionar Livro:** Cadastro de novos exemplares com validação de duplicidade (título e autor).
- **Editar Livro:** Atualização dinâmica de dados de um livro existente através de seu ID.
- **Buscar Livro:** Consulta rápida e individual de livros pelo ID.
- **Listar Todos:** Exibição completa do acervo salvo no banco de dados.
- **Deletar Livro:** Exclusão segura de registros.
- **Relatório por Preço:** Filtragem dinâmica de livros baseada em um valor máximo definido pelo usuário.

## 🛠️ Tecnologias e Arquitetura

- **Java (JDK 21+)**
- **PostgreSQL** (Banco de Dados Relacional)
- **JDBC** (Java Database Connectivity) para comunicação com o banco.
- **Arquitetura em Camadas:**
    - `entities`: Modelos de domínio (`Book`).
    - `dao`: Padrão Data Access Object e Factory para isolar a persistência de dados.
    - `service`: Camada de blindagem contendo as regras de negócio e validações.
    - `cli`: Interface de interação com o usuário.

## 🚀 Como executar o projeto na sua máquina

### 1. Pré-requisitos
- Ter o **Java (JDK)** instalado.
- Ter o **PostgreSQL** instalado e rodando.

### 2. Configuração do Banco de Dados
Crie um banco de dados no seu PostgreSQL e execute o script abaixo para criar a tabela necessária:

```sql
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    preco NUMERIC(10, 2)
);
```

### 3. Configuração de Credenciais
Para que o sistema consiga se conectar ao seu banco de dados local, você precisará criar o arquivo de propriedades.

1. Na pasta `src/resources/`, crie um arquivo chamado `db.properties`.
2. Adicione as suas credenciais do PostgreSQL neste formato:

```properties
user=seu_usuario_aqui
password=sua_senha_aqui
dburl=jdbc:postgresql://localhost:5432/nome_do_seu_banco
```

### 4. Execução
Execute a classe Main.java através da sua IDE de preferência ou compile o projeto via terminal para iniciar o menu interativo.

---
*Desenvolvido como desafio prático de aprimoramento em Java e Banco de Dados utilizando JDBC Puro.*

