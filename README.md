<p align="center">
   <img src="src/file_0000000044f4622f901e024962e09999.png" " alt="Banner Literatura">
</p>

# 📚 Literalura

Bem-vindo(a) ao **Literalura**! Este projeto é uma aplicação console desenvolvida em Java com Spring Boot, que permite interagir com a API do Gutendex para buscar e gerenciar informações sobre livros e autores.

Você pode buscar livros por título, listar livros e autores registrados, encontrar autores vivos em um determinado ano, e mais, tudo isso com persistência de dados em um banco de dados PostgreSQL.

---

## 📋 Índice
- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [🚀 Como Executar o Projeto](#-como-executar-o-projeto)
    * [Pré-requisitos](#pré-requisitos)
    * [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
    * [Executando a Aplicação](#executando-a-aplicação)
- [📖 Uso](#-uso)
- [🤝 Contribuição](#-contribuição)
- [📜 Licença](#-licença)
- [💬 Contato](#-contato)
- [🎯 Conclusão](#-conclusão)

---

## ✨ Funcionalidades

A aplicação Literalura oferece as seguintes funcionalidades através de um menu interativo:

- **Buscar livro pelo título:** Pesquise um livro na API do Gutendex e salve-o no banco de dados, incluindo seus detalhes e autor.
- **Listar livros registrados:** Visualize todos os livros que foram salvos em seu banco de dados, ordenados por título.
- **Listar autores registrados:** Visualize todos os autores únicos que foram salvos, com seus anos de nascimento e falecimento, e os livros associados a cada um.
- **Listar autores vivos em um determinado ano:** Encontre autores que estavam vivos durante um ano específico, com base em suas datas de nascimento e falecimento.
- **Listar livros em determinado idioma:** Filtre os livros registrados por um idioma específico.
- **Top 5 livros mais baixados:** Veja uma lista dos cinco livros mais baixados da API, com base nos dados registrados.

---

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias:

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* **Jackson**
* **Gutendex API**
---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação em sua máquina.

### Pré-requisitos

Certifique-se de ter o seguinte instalado:

* [JDK (Java Development Kit) 17 ou superior](https://www.oracle.com/java/technologies/downloads/)
* [Maven](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/)
* [pgAdmin 4](https://www.pgadmin.org/download/) (Opcional, mas recomendado para gerenciar o banco de dados)

### Configuração do Banco de Dados

1.  **Crie um Banco de Dados:**
    * Abra o pgAdmin 4.
    * Conecte-se ao seu servidor PostgreSQL.
    * Clique com o botão direito em `Databases`, selecione `Create` > `Database...`.
    * Nomeie o banco de dados como `literalura_db` (ou outro nome de sua preferência).

2.  **Configure o `application.properties`:**
    * No seu projeto, localize o arquivo `src/main/resources/application.properties`.
    * Atualize as configurações de conexão com o seu banco de dados PostgreSQL:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
    spring.datasource.username=seu_usuario_postgresql
    spring.datasource.password=sua_senha_postgresql
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```
    
    * `spring.jpa.hibernate.ddl-auto=update` fará com que o Hibernate crie e atualize as tabelas automaticamente com base nas suas entidades JPA.

### Executando a Aplicação

1.  **Clone o Repositório:**
     ```bash
    git clone https://github.com/seu-usuario/challenge-literatura.git
    ```

2.  **Compile o Projeto (Opcional, mas recomendado):**
    ```bash
    mvn clean install
    ```

3.  **Execute a Aplicação:**
    Você pode executar a aplicação diretamente da sua IDE (IntelliJ IDEA, Eclipse, etc.) ou via linha de comando:
    ```bash
    mvn spring-boot:run
    ```

    A aplicação iniciará e o menu principal será exibido no console.

---

## 📖 Uso

Após iniciar a aplicação, um menu interativo será apresentado:

```text
******************************
   BEM VINDO AO LITERALURA!
******************************

1 - Buscar livro pelo titulo
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em determinado idioma
6 - Top 5 livros mais baixados
0 - sair

Escolha uma opcao valida:
```

Digite o número da opção desejada e pressione `Enter`. Siga as instruções no console para interagir com a aplicação.

---

## 🤝 Contribuição

Contribuições são bem-vindas! Se você quiser melhorar este projeto:

1. Faça um fork do repositório.
2. Crie uma nova branch (`git checkout -b feature/sua-feature`).
3. Faça suas alterações e commit-as (`git commit -m 'feat: Adiciona nova funcionalidade X'`).
4. Envie para a branch (`git push origin feature/sua-feature`).
5. Abra um Pull Request.

---

## 📜 Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 💬 Contato

**Gabriela de Oliveira** - [gabiolli](https://github.com/gabiolli)

 ---
 
## 🎯 Conclusão

Este projeto foi desenvolvido como parte da minha jornada de aprendizado e especialização em programação, especificamente no programa **Oracle ONE (Oracle Next Education)** em parceria com a **Alura**. Ele representa a aplicação de conceitos fundamentais de Java, Spring Boot, persistência de dados com JPA/Hibernate, e integração com APIs externas, consolidando o conhecimento adquirido ao longo do curso. Foi uma experiência valiosa para construir uma aplicação funcional do início ao fim.

