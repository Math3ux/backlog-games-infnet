# Matheus API

## Etapa 1: Organização Arquitetural

### Identificação dos módulos da aplicação
A aplicação foi reorganizada para utilizar uma arquitetura orientada a domínio, abandonando a separação estritamente técnica baseada no modelo MVC. Foram identificados três módulos principais:

* **Jogo** — Responsável pelo registo, manutenção e consulta do catálogo de jogos internos da aplicação (incluindo as especificidades de jogos físicos e digitais).
* **Desenvolvedora** — Responsável por gerir os dados das empresas que produzem os jogos e os seus relacionamentos.
* **Integração** — Responsável por toda a comunicação externa, especificamente a consulta de dados à API pública da CheapShark.

### Análise de dependências
Na estrutura atual, os módulos relacionam-se internamente, o que caracteriza o acoplamento da aplicação:

**Jogo → Integração**
O módulo de `Jogo` precisa de invocar os serviços do módulo de `Integração` para enriquecer os dados ou validar informações (através do `JogoExternoDTO`) consultando a API da CheapShark antes de persistir um jogo localmente.

### Candidato a serviço independente
* **Funcionalidade escolhida:** Consulta a informações externas (Módulo de `Integração` / CheapShark).
* **Responsabilidade:** Realizar chamadas de rede a fornecedores externos (CheapShark API), tratar as respostas e disponibilizar esses dados de forma limpa e padronizada.
* **Por que poderia ser executada separadamente:** A comunicação com APIs externas é propensa a falhas de rede, lentidão e bloqueios por limite de taxa (rate limiting). Se esta responsabilidade for isolada num microsserviço, a aplicação principal poderá continuar a gerir o catálogo interno de jogos sem ser afetada caso a API externa fique indisponível, aumentando a resiliência do sistema.
* **Partes que dependem dela:** Atualmente, as regras de negócio inseridas no módulo de `Jogo` dependem desta funcionalidade para obter dados remotos.

## Etapa 2: Separação e Comunicação entre Serviços

### Definição da Responsabilidade
* **Nome do serviço:** `integracao-service`
* **Responsabilidade principal:** Atuar como um *Gateway* de domínio para comunicação com APIs externas, isolando a lógica de integração e filtragem de dados de provedores como a CheapShark.
* **Funcionalidade separada:** A chamada direta à API da CheapShark foi removida da aplicação principal (`matheus-api`).
* **Motivo da separação:** A comunicação com APIs de terceiros depende de I/O de rede e é suscetível a indisponibilidades externas. Ao isolar esta responsabilidade, protegemos a aplicação principal de lentidões e falhas que não estão sob nosso controle.

### Reflexão Arquitetural
* **Qual funcionalidade foi separada?** A consulta de títulos e extração de capas de jogos na API da CheapShark.
* **Por que ela foi escolhida?** Porque representa uma dependência externa. É o ponto de maior risco de falha na aplicação e possui um contexto muito bem delimitado que não afeta as regras de negócio internas de cadastro de jogos.
* **O que ficou mais complexo?** A orquestração local e o rastreamento de erros. Agora é necessário garantir que dois serviços estejam rodando simultaneamente em portas diferentes, além de exigir a implementação de um mecanismo de tolerância a falhas na aplicação principal.
* **O que aconteceria se o novo serviço ficasse indisponível?** Devido ao tratamento de exceções implementado no `JogoService`, a aplicação principal (`matheus-api`) continua funcionando normalmente. O jogo é persistido no banco de dados, apenas com o campo `urlCapa` vazio, garantindo a resiliência do sistema e uma boa experiência para o cliente.
* **Poderia continuar dentro da aplicação?** Sim. Se a aplicação não possuir uma carga alta que justifique a escalabilidade independente desse módulo, mantê-la no monólito reduziria a complexidade de deploy e infraestrutura. A separação é uma decisão de *trade-off* arquitetural.
