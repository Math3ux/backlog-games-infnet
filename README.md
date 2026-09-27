# Matheus API - Etapa 1: Organização Arquitetural

## Identificação dos módulos da aplicação

A aplicação foi reorganizada para utilizar uma arquitetura orientada a domínio, abandonando a separação estritamente técnica baseada no modelo MVC. Foram identificados três módulos principais:

* **Jogo** — Responsável pelo registo, manutenção e consulta do catálogo de jogos internos da aplicação (incluindo as especificidades de jogos físicos e digitais).
* **Desenvolvedora** — Responsável por gerir os dados das empresas que produzem os jogos e os seus relacionamentos.
* **Integração** — Responsável por toda a comunicação externa, especificamente a consulta de dados à API pública da CheapShark.

## Análise de dependências

Na estrutura atual, os módulos relacionam-se internamente, o que caracteriza o acoplamento da aplicação:

**Jogo → Integração**
O módulo de `Jogo` precisa de invocar os serviços do módulo de `Integração` para enriquecer os dados ou validar informações (através do `JogoExternoDTO`) consultando a API da CheapShark antes de persistir um jogo localmente.

## Candidato a serviço independente

* **Funcionalidade escolhida:** Consulta a informações externas (Módulo de `Integração` / CheapShark).
* **Responsabilidade:** Realizar chamadas de rede a fornecedores externos (CheapShark API), tratar as respostas e disponibilizar esses dados de forma limpa e padronizada.
* **Por que poderia ser executada separadamente:** A comunicação com APIs externas é propensa a falhas de rede, lentidão e bloqueios por limite de taxa (rate limiting). Se esta responsabilidade for isolada num microsserviço, a aplicação principal poderá continuar a gerir o catálogo interno de jogos sem ser afetada caso a API externa fique indisponível, aumentando a resiliência do sistema.
* **Partes que dependem dela:** Atualmente, as regras de negócio inseridas no módulo de `Jogo` dependem desta funcionalidade para obter dados remotos.