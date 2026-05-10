# Backend Cadastro e Login

API Java com Spring Boot para tela de cadastro e tela de login.

## Banco MySQL

O projeto esta configurado para criar o banco e as tabelas automaticamente:

- Banco: `cadastro_db`
- Tabela de cadastro: `cadastro`
- Tabela de login: `login`
- Usuario MySQL: `root`
- Senha MySQL: 7133642012

Se seu MySQL tiver senha, altere em `src/main/resources/application.properties`:

```properties
spring.datasource.password=7133642012
```

## Rodar o projeto

Com MySQL:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Para testar imediatamente sem MySQL, use o perfil local com H2:

```bash
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

A API sobe em:

```text
http://localhost:8080
```

## Testar cadastro

POST `http://localhost:8080/cadastro`

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "senha": "123456"
}
```

Esse endpoint salva na tabela `cadastro` e tambem cria o registro de acesso na tabela `login`.

GET `http://localhost:8080/cadastro`

Lista os cadastros salvos.

## Testar login

POST `http://localhost:8080/login`

```json
{
  "email": "maria@email.com",
  "senha": "123456"
}
```

Resposta de sucesso:

```json
{
  "autenticado": true,
  "mensagem": "Login realizado com sucesso"
}
```

GET `http://localhost:8080/login`

Lista os dados salvos na tabela `login`.

## Rodar testes automatizados

```bash
mvnw.cmd test
```

Os testes usam H2 em memoria, entao nao precisam do MySQL aberto.
