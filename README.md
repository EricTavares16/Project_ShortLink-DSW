# ShortLink - Encurtador de Links

Projeto desenvolvido em **Java com Spring Boot** para a disciplina de Desenvolvimento de Sistemas Web.  
O objetivo é criar uma aplicação de encurtamento de URLs, com suporte a **usuários**, **grupos**,  **Historico de Movimentação** e **gerenciamento de links curtos**.

---

## Domínio Modelado

O sistema simula um serviço de encurtamento de links, mas com funcionalidades extras de organização e controle.

### Entidades principais:

- **User**: representa o criador dos links e grupos.
- **Link**: representa um link encurtado, pertencente a um usuário e, opcionalmente, a um grupo.
- **Group**: permite organizar links em categorias ou campanhas.

---

## Justificativa das Entidades

| Entidade | Função | Justificativa |
|-----------|--------|----------------|
| **User** | Representa um usuário do sistema | Necessário para autenticação e associação de links criados |
| **Link** | Encurtamento de URLs originais | É o núcleo do sistema, o que dá propósito à aplicação |
| **Group** | Agrupar e organizar links | Facilita o gerenciamento e análise de campanhas |


Endpoints Principais

Usuários

Criar usuário


POST /api/users

Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "123456"
}



Banco de Dados
Banco em memória: H2
URL de acesso: jdbc:h2:mem:librarydb
Usuário: sa
Senha: (vazia)


![Diagrama Conceitual](DiagramaConceitualshortlink.png)
