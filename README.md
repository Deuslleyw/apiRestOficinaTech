# API Oficina 🔧

API REST para sistema de gestão de oficina mecânica, desenvolvida com Spring Boot. Permite gerenciar clientes, veículos e ordens de serviço com autenticação JWT.

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.6 |
| Spring Security | 7.x |
| Spring Data JPA | 4.x |
| PostgreSQL | 15+ |
| MapStruct | 1.5.x |
| Lombok | 1.18.x |
| JJWT | 0.12.3 |
| Bean Validation | 3.x |
| Maven | 3.9.x |

---

## Arquitetura

O projeto segue uma arquitetura em camadas com os seguintes pacotes:

```
src/main/java/com/deusleyDev/apiOficina/
├── controller/         # Endpoints REST
├── service/            # Interfaces de serviço
│   └── impl/           # Implementações dos serviços
├── domain/             # Entidades JPA
├── Dto/                # DTOs de Request e Response
│   ├── cliente/
│   ├── veiculo/
│   ├── ordemServico/
│   └── usuario/
├── mapper/             # Mappers MapStruct
├── repositories/       # Repositórios JPA
├── exceptions/         # Exceções customizadas e handler global
├── enuns/              # Enums do sistema
└── jwt/                # Configuração JWT e Spring Security
```

**Padrões adotados:**
- `Request` recebe IDs para relacionamentos
- `Response` retorna objetos completos enriquecidos
- Service com interface + implementação
- Fluxo: `DTO → Mapper → Entity → Repository → Entity → Mapper → Response`

---

## Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação stateless.

### Fluxo

```
1. POST /api/v1/auth/login  →  { email, senha }
2. Recebe token JWT
3. Inclui token no header de todas as requisições protegidas:
   Authorization: Bearer <token>
```

### Perfis de acesso

| Perfil | Permissões |
|---|---|
| `ADMIN` | Acesso total, incluindo gerenciamento de usuários |
| `USUARIO` | Acesso a todas as rotas, exceto `/api/v1/usuario/**` |

---

## Endpoints

### Auth

| Método | Rota | Descrição | Autenticação |
|---|---|---|---|
| `POST` | `/api/v1/auth/login` | Realiza login e retorna token JWT | Pública |

**Request:**
```json
{
  "email": "admin@oficina.com",
  "senha": "suaSenha"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "tipo": "Bearer",
  "nome": "Administrador",
  "perfil": "ADMIN"
}
```

---

### Cliente

| Método | Rota | Descrição | Perfil |
|---|---|---|---|
| `POST` | `/api/v1/cliente` | Cadastra novo cliente | ADMIN, USUARIO |
| `GET` | `/api/v1/cliente` | Lista todos os clientes | ADMIN, USUARIO |
| `GET` | `/api/v1/cliente/{id}` | Busca cliente por ID | ADMIN, USUARIO |
| `PUT` | `/api/v1/cliente/{id}` | Atualiza cliente | ADMIN, USUARIO |
| `DELETE` | `/api/v1/cliente/{id}` | Remove cliente | ADMIN, USUARIO |

**Request:**
```json
{
  "nome": "João Silva",
  "telefone": "48999998888",
  "email": "joao@email.com",
  "cpf": "12345678901"
}
```

**Response:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "telefone": "48999998888",
  "email": "joao@email.com",
  "cpf": "12345678901"
}
```

---

### Veículo

| Método | Rota | Descrição | Perfil |
|---|---|---|---|
| `POST` | `/api/v1/veiculo` | Cadastra novo veículo | ADMIN, USUARIO |
| `GET` | `/api/v1/veiculo` | Lista todos os veículos | ADMIN, USUARIO |
| `GET` | `/api/v1/veiculo/{id}` | Busca veículo por ID | ADMIN, USUARIO |
| `PUT` | `/api/v1/veiculo/{id}` | Atualiza veículo | ADMIN, USUARIO |
| `DELETE` | `/api/v1/veiculo/{id}` | Remove veículo | ADMIN, USUARIO |

**Request:**
```json
{
  "marca": "Chevrolet",
  "modelo": "Onix",
  "placa": "ABC-1234",
  "ano": 2022,
  "clienteId": 1
}
```

**Response:**
```json
{
  "id": 1,
  "placa": "ABC-1234",
  "modelo": "Onix",
  "marca": "Chevrolet",
  "ano": "2022",
  "clienteId": 1,
  "clienteNome": "João Silva"
}
```

---

### Ordem de Serviço

| Método | Rota | Descrição | Perfil |
|---|---|---|---|
| `POST` | `/api/v1/ordem-servico` | Abre nova ordem de serviço | ADMIN, USUARIO |
| `GET` | `/api/v1/ordem-servico` | Lista todas as ordens | ADMIN, USUARIO |
| `GET` | `/api/v1/ordem-servico/{id}` | Busca ordem por ID | ADMIN, USUARIO |
| `PUT` | `/api/v1/ordem-servico/{id}` | Atualiza ordem de serviço | ADMIN, USUARIO |
| `PATCH` | `/api/v1/ordem-servico/{id}/cancelar` | Cancela ordem de serviço | ADMIN, USUARIO |

> **Nota:** Ordens de serviço não são deletadas — apenas canceladas. Isso garante rastreabilidade e histórico para fins de garantia.

**Status disponíveis:** `ABERTA` `EM_ANDAMENTO` `CONCLUIDA` `CANCELADA`

**Request:**
```json
{
  "descricao": "Troca de óleo e filtro",
  "valor": 150.00,
  "status": "ABERTA",
  "clienteId": 1,
  "veiculoId": 1
}
```

**Response:**
```json
{
  "id": 1,
  "descricao": "Troca de óleo e filtro",
  "valor": 150.0,
  "status": "ABERTA",
  "dataAbertura": "2026-10-15T10:00:00.000000",
  "dataFechamento": null,
  "cliente": {
    "id": 1,
    "nome": "João Silva",
    "telefone": "48999998888",
    "email": "joao@email.com"
  },
  "veiculo": {
    "id": 1,
    "placa": "ABC-1234",
    "modelo": "Onix",
    "marca": "Chevrolet",
    "ano": "2022",
    "clienteId": 1,
    "clienteNome": "João Silva"
  }
}
```

---

### Usuário

| Método | Rota | Descrição | Perfil |
|---|---|---|---|
| `POST` | `/api/v1/usuario` | Cadastra novo usuário | ADMIN |
| `GET` | `/api/v1/usuario` | Lista todos os usuários | ADMIN |

**Request:**
```json
{
  "nome": "Carlos Souza",
  "email": "carlos@oficina.com",
  "senha": "senha123",
  "perfil": "USUARIO"
}
```

**Response:**
```json
{
  "id": 2,
  "nome": "Carlos Souza",
  "email": "carlos@oficina.com",
  "perfil": "USUARIO"
}
```

---

## Regras de Negócio

- **Veículo pertence ao cliente:** Ao criar ou atualizar uma ordem de serviço, o veículo informado deve pertencer ao cliente informado.
- **Data de abertura automática:** Preenchida automaticamente no momento da criação da OS.
- **Data de fechamento automática:** Preenchida automaticamente quando o status é alterado para `CONCLUIDA`.
- **Cancelamento em vez de exclusão:** Ordens de serviço não podem ser deletadas — apenas canceladas via `PATCH /{id}/cancelar`.
- **Proteção de exclusão de cliente:** Clientes com ordens de serviço vinculadas não podem ser excluídos.
- **Proteção de exclusão de veículo:** Veículos com ordens de serviço vinculadas não podem ser excluídos.
- **Validação de IDs:** Antes de salvar, o sistema valida se `clienteId` e `veiculoId` existem no banco.
- **Email único para usuários:** Não é possível cadastrar dois usuários com o mesmo email.

---

## Tratamento de Erros

Todos os erros seguem o padrão:

```json
{
  "timestamp": "2026-10-15T10:00:00.000000",
  "status": 404,
  "error": "Cliente não encontrado com id: 99",
  "path": "/api/v1/cliente/99"
}
```

| Código HTTP | Situação |
|---|---|
| `400 Bad Request` | Dados inválidos na requisição (validação) |
| `401 Unauthorized` | Token ausente ou inválido |
| `403 Forbidden` | Sem permissão para acessar o recurso |
| `404 Not Found` | Recurso não encontrado |
| `422 Unprocessable Entity` | Violação de regra de negócio ou integridade |
| `500 Internal Server Error` | Erro interno no servidor |

---

## Validações

| Campo | Validação |
|---|---|
| `nome` | Obrigatório, entre 3 e 100 caracteres |
| `email` | Obrigatório, formato válido de email |
| `telefone` | Obrigatório, entre 10 e 15 caracteres |
| `cpf` | Obrigatório, exatamente 11 dígitos numéricos |
| `placa` | Obrigatória, entre 7 e 8 caracteres |
| `ano` | Obrigatório, entre 1900 e 2100 |
| `valor` | Obrigatório, maior que zero |
| `status` | Obrigatório, valor válido do enum |
| `clienteId` | Obrigatório |
| `veiculoId` | Obrigatório |
| `senha` | Obrigatória |
| `perfil` | Obrigatório, `ADMIN` ou `USUARIO` |

---

## Variáveis de Ambiente

| Variável | Descrição | Exemplo |
|---|---|---|
| `ADMIN_EMAIL` | Email do administrador inicial | `admin@oficina.com` |
| `ADMIN_SENHA` | Senha do administrador inicial | `senhaForte@123` |
| `ADMIN_NOME` | Nome do administrador inicial | `Administrador` |

As variáveis JWT e banco de dados são configuradas nos arquivos `application.yml` e `application-dev.yml`.

> **Atenção:** Os arquivos `application-dev.yml` e `application-prod.yml` estão no `.gitignore` e não devem ser versionados.

---

## Como Executar

### Pré-requisitos

- Java 21+
- Maven 3.9+
- PostgreSQL 15+

### Passo a passo

**1. Clone o repositório:**
```bash
git clone https://github.com/deusleyDev/apiOficina.git
cd apiOficina
```

**2. Crie o banco de dados:**
```sql
CREATE DATABASE oficina;
```

**3. Configure o `application-dev.yml`:**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/oficina
    username: seu_usuario
    password: sua_senha
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
```

**4. Configure as variáveis de ambiente na IDE (IntelliJ):**
```
ADMIN_EMAIL=admin@oficina.com;ADMIN_SENHA=suaSenha;ADMIN_NOME=Administrador
```

**5. Execute o projeto:**
```bash
./mvnw spring-boot:run
```

**6. Acesse a API:**
```
http://localhost:8080
```

---

## Primeiro acesso

Ao iniciar a aplicação pela primeira vez, um usuário administrador é criado automaticamente com as credenciais definidas nas variáveis de ambiente.

**Login:**
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "admin@oficina.com",
  "senha": "suaSenha"
}
```

Use o token retornado no header de todas as requisições:
```
Authorization: Bearer <token>
```

---

## Segurança

- Senhas armazenadas com hash **BCrypt**
- Autenticação **stateless** via JWT
- Token com expiração de **24 horas**
- Credenciais do admin via **variáveis de ambiente**
- Arquivos sensíveis ignorados pelo **Git**

---

## Autor

```
Desenvolvido por:  Deusley Diego - @DeusleyDev 
```
***