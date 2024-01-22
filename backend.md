# Tecnologias Utilizadas

* spring-web
* spring-data-jpa
* flyway-core
* lombok
* springdoc-openapi-starter-webmvc-ui
* spring-boot-starter-validation
* spring-boot-starter-actuator
* H2

## Escolha da tecnologia

* spring web foi escolhido para usar do contexto sincrono de api, permitindo fazer requisições com verbos http, juntamente com springdoc criando uma documentação para cada endpoint no sistema de forma automatica e simplificada

* spring data jpa usado para fazer requisições no banco de dados H2 para trazer os dados, deletar e manipula-los de mais abstraida

* lombok foi utilizado para reduzir codigo muitas vezes repetitivos como getters e setter além de contrutores

* flyway foi utilizado para facilitar a criação das tabelas em tempo de desenvolvimento retirando a necessidade de criar as tabelas manualmente como pode acontecer ao restartar um projeto com H2

* spring boot stater actuator foi utilizado para conseguir ver o status do serviço de forma basica, como se o status está "ok"

## Arquitetura do Sistema

Foi escolhido o modelo em camadas pela sua abstração e facilidade em ser aplicada, onde temos a cama de config "configuração", service, implements"impl", controller, repository
e domain, onde na domain seria desenvolvido as entidades do banco, dto´s, e enums utilizado em si, a pasta schedule foi criada com o intuito de gerar uma tarefa agendada que rodasse as 9 e ás 17 da manhã para verificar transferencias pendentes e realizalas de imediato

## Rodar o projeto

    mvn spring-boot:run na pasta inicial "teste"

a porta 8080 será utilizada e para visualizar o swagger será preciso ir nesta url
<http://localhost:8080/>
<http://localhost:8080/swagger-ui/index.html>
