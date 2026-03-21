# Backend Project - API + JavaFX Client

## Sobre o projeto

Este projeto consiste principalmente em uma **API REST desenvolvida com Spring Boot**, responsável por gerenciar usuários, autenticação e integração com banco de dados.

O cliente JavaFX atua como consumidor da API, sendo apenas uma interface para interação.

A aplicação permite:

* Registro e login de usuários
* Persistência de dados em banco MySQL
* Consumo de API externa para geração de usuários aleatórios

---

## Arquitetura

O projeto está organizado em múltiplos módulos:

```
Project_Back_end/
 ├── mysqlDB            # API REST (Spring Boot - núcleo do sistema)
 ├── cosumir_api_facil  # Cliente HTTP (Java)
 ├── appRandomUsers     # Interface gráfica (JavaFX)
```

A API (`mysqlDB`) é o componente principal, responsável por toda a lógica de negócio e comunicação com o banco de dados.

---

## Tecnologias utilizadas

* Java
* Spring Boot
* MySQL
* Maven
* JavaFX
* JSON (org.json)

---

## Funcionalidades

### Autenticação

* Login com validação de dados
* Registro de novos usuários

### API REST

* `GET /get` → retorna usuários do banco
* `POST /post` → cadastra novos usuários

### Integração externa

* Consumo de API de usuários aleatórios

---

## Configuração de ambiente

Este projeto utiliza variáveis de ambiente para armazenar informações sensíveis.

Crie um arquivo `.env` na raiz do projeto com base no exemplo abaixo:

```
API_DB_KEY=sua_api_key
API_DB=http://localhost:8080
API_RAND_USERS=https://api.exemplo.com
```

### Importante

* O arquivo `.env` não deve ser versionado (não subir para o GitHub)
* Utilize um arquivo `.env.example` como modelo para outros desenvolvedores

---

## Como executar

### 1. Rodar a API (Spring Boot)

```
cd mysqlDB
mvn spring-boot:run
```

---

### 2. Rodar o cliente JavaFX

Abra o projeto `appRandomUsers` no IntelliJ e execute a aplicação.

---

## Observações

* A API utiliza API Key para autenticação simples
* Senhas ainda estão em texto puro (melhoria futura necessária)
* Estrutura ainda pode ser evoluída para padrão em camadas (Controller / Service / Repository)

---

## Melhorias futuras

* Implementar criptografia de senha (BCrypt)
* Separação em camadas (Service / Repository)
* Implementar autenticação com JWT
* Melhorar padronização das respostas da API
* Evoluir arquitetura para padrões mais escaláveis

---

## Autor

Projeto desenvolvido com foco em evolução na área de backend utilizando Java e Spring Boot.

---

## Objetivo

Este projeto tem como objetivo demonstrar conhecimentos em:

* Desenvolvimento de APIs REST
* Integração com banco de dados
* Estruturação de backend
* Consumo de APIs externas
---
* Projeto ainda em desenvolvimento
