# # Curso Spring MVC com Thymeleaf
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/emerson-roots/curso-spring-MVC-thymeleaf/blob/master/LICENSE) 

# Sobre o projeto

https://curso-spring-mvc-thymeleaf.herokuapp.com/

No curso desenvolvemos uma aplicação web de cadastros de Departamentos onde há Cargos associados e também Funcionários. O [Curso Udemy](https://www.udemy.com/course/spring-boot-mvc-com-thymeleaf/ "Link do curso") tem duração aproximada de 9 horas em video distribuidas em mais de 80 aulas.

## Demonstração de telas e layouts web

## View *Cadastro de Funcionários*
![View Cadastro Funcionario](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/tela%20cadastro%20funcionario.png) 

## View cadastro de funcionários e suas *Validações Campo a Campo*
![View Cadastro Funcionario - Validação Campo a campo](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/tela%20cadastro%20-%20validacao%20campo%20a%20campo.png)

## View Listagem de Funcionários *com pesquisa por 03 campos diferentes*
![View Listar Funcionarios](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/tela%20listar%20funcionarios.png)

## Lista de cargos *com paginação e ordenação alfabética na tabela*
![View Listar Cargos](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/tela%20listar%20cargos%20-%20com%20paginacao%20e%20classificacao%20alfabetica.png)

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
## Front end
- HTML / CSS / JS 
- Bootstrap 4
- Webjars
- Thymeleaf
## Implantação em produção
- Back end/Front end: Heroku
- Banco de dados: MySql

# Como executar o projeto

## Pré-requisitos: 
- Java 11
- MySql Online (não é preciso criar a base de dados, a aplicação cria automaticamente)


```bash
# na linha de comando, clonar repositório
git clone https://github.com/emerson-roots/curso-spring-MVC-thymeleaf

# entrar na pasta do projeto back end

# fazer build no projeto pelo Maven
mvn package -e

# entrar na pasta /target e executar a aplicação (bd deve estar on)
java -jar curso_spring_boot_MVC_com_thymeleaf-0.0.1-SNAPSHOT.jar

# teste a aplicação no navegador
http://localhost:8080/

# pausar/stopar aplicação
CTRL+C na linha de comando
```


# Autor

*Emerson Melo de Lima*

emerson_sardinha@hotmail.com
