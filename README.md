# 🎮 API de Gerenciamento de Backlog de Jogos

Projeto desenvolvido como avaliação acadêmica, consistindo em uma API RESTful em Java e Spring Boot para o gerenciamento de catálogos e backlogs de jogos (Físicos e Digitais). O sistema evoluiu progressivamente cobrindo fundamentos de Orientação a Objetos até integrações Externas.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.3.2
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (In-Memory)
* **Integração Externa:** Spring Cloud OpenFeign
* **Documentação:** Swagger / OpenAPI (Springdoc)
* **Validação:** Jakarta Bean Validation

---

## 📈 Evolução do Projeto (Tags)

O projeto foi construído em quatro etapas, todas registradas e acessíveis via tags no repositório:

### Orientação a Objetos Avançada
* Criação do modelo de domínio com as entidades `Desenvolvedora`, `Jogo` (Abstrata), `JogoDigital` e `JogoFisico`.
* Implementação de herança, encapsulamento e polimorfismo.
* Relacionamento bidirecional Um-para-Muitos (`Desenvolvedora` -> `Jogos`).
* Uso de tipos variados (String, Long, Double, Boolean, Integer, LocalDate) e sobrescrita do método `toString()`.

### Collections, Streams e Camada de Serviço
* Encapsulamento da lógica de negócios na classe `JogoService`.
* Persistência temporária estruturada via `Map`.
* Manipulação avançada de coleções utilizando a API de **Streams e Lambdas** (filtragem de backlog, ordenação por nota, transformação de dados).
* Tratamento customizado de exceções (`JogoNaoEncontradoException`).

### API REST com Spring Boot
* Exposição das regras de negócio via requisições HTTP (GET, POST, PUT, DELETE) na camada de `Controller`.
* Configuração do `GlobalExceptionHandler` (ControllerAdvice) para garantir códigos HTTP semânticos (200, 201, 204, 400, 404).
* Geração automática e interativa de documentação com **Swagger UI**.
* Injeção de dependências realizada via construtores (melhor prática).

### JPA, Validações e Banco de Dados
* Refatoração da persistência de memória (Map) para o banco relacional **H2**.
* Mapeamento ORM utilizando anotações JPA (`@Entity`, `@OneToMany`, `@ManyToOne`, `@Inheritance`).
* Resolução de referências circulares de JSON usando `@JsonIgnore`.
* Implementação da interface `JpaRepository` com consultas derivadas (`findByIsFinalizadoFalse`).
* Proteção dos *endpoints* utilizando Bean Validation (`@NotNull`, `@NotBlank`, `@Min`).

### 🚀 Integração com API Externa
Foi implementada uma integração com a **API pública do CheapShark**. Ao cadastrar um novo jogo enviando apenas o título via POST, a API utiliza o **OpenFeign** para realizar uma chamada externa, intercepta a requisição para injetar o `User-Agent` exigido pelo provedor, captura a URL da capa oficial do jogo e salva automaticamente no banco de dados H2.

---

## ⚙️ Como Executar e Testar

1. Clone o repositório.
2. Atualize as dependências do Maven.
3. Execute a classe principal `MatheusApiApplication.java` pela sua IDE.

**Acessando a Documentação (Swagger):**
Com a aplicação em execução, abra o navegador e acesse:
> `http://localhost:8080/swagger-ui.html`

Nesta interface, você poderá realizar requisições POST para `/api/jogos/digital` ou `/api/jogos/fisico` e testar todas as funcionalidades visualmente.

**Acessando o Banco de Dados (H2 Console):**
Para consultar as tabelas geradas e os dados persistidos:
1. Acesse: `http://localhost:8080/h2-console`
2. Utilize as credenciais:
    * **JDBC URL:** `jdbc:h2:mem:backlogdb`
    * **User Name:** `sa`
    * **Password:** `password`