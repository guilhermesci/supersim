# SuperSim Project

Essa aplicação foi criada utilizando Java 11 com o framework Spring no _back-end_ e React no _front-end_.

Não possuia nenhuma experiência com React, este é o primeiro projeto que utilizo. 
Para o desenvolvimento do frontend, tomei como base o repositório https://github.com/acenelio/dsmovie.

## Objetivo

Ao final do projeto teremos uma aplicação que realiza o gerenciamento de clientes e seus empréstimos. Futuramente outras funcionalidades serão adicionadas.

*No primeiro momento as funcionalidades disponíveis são: Listagem, Cadastro, Edição e Exclusão de Clientes.*

## Requisitos

É necessário [`java 11`](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html), [`maven`](https://maven.apache.org/install.html), 
[`npm`](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) e [`yarn`](https://classic.yarnpkg.com/en/docs/install#windows-stable) instalados.

_Observação:_
Foram criadas imagens docker para o frontend e backend. Caso deseje apenas executar a aplicação pelo Docker sem a necessidade de baixar o projeto todo, 
deverá instalar o `Docker` e copiar para um mesmo diretório apenas os arquivos `docker-compose.yml` e `init.sql`.

## Configuração

Foram criados 3 profiles no projeto spring, `test`, `dev` e `prod` que podem ser encontrados em `backend/src/main/resources`. 

Para execução local do projeto deverá selecionar o profile _test_ ou _dev_. Na configuração `test` não é necessária nenhuma configuração adicional, 
nela temos a utilização do banco de dados H2. Já na configuração `dev` utilizamos o banco de dados postgresql e caso selecionada, se faz necessário 
possuir o postgres instalado e atender suas configurações de url, username e password.

## Execução Docker

Acessar o diretório em que estão os arquivos `docker-compose.yml` e `init.sql` e executar o comando:
~~~
  docker-compose up
~~~

## Execução FrontEnd | BackEnd

Acessar o diretório `frontend` e executar o comando:
~~~
  yarn start
~~~

No caminho `backend/src/main/resources`, altere a primeira linha do arquivo `aplication.properties` informando o profile que deseja executar:
~~~
  spring.profiles.active=test
  ou
  spring.profiles.active=dev
~~~

Em seguida, acesse o diretório `backend` e execute o comando:
~~~
  mvn spring-boot:run
~~~

A aplicação estará funcionando no endereço [http://localhost:3000/](http://localhost:3000/).

## Execução Tests BackEnd

No diretório `backend` e execute o comando:
~~~
  mvn test
~~~
