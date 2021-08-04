# Spring Boot | Spring MVC | Spring Security | JSON Web Token (JWT) | Angular 12 como contribuição e portfolio
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/emerson-roots/curso-spring-MVC-thymeleaf/blob/master/LICENSE) 

## Este projeto contempla 02 aplicações/versões distintas e suas modificações podem ser observadas em 03 branchs conforme descrito abaixo.

### DESCRIÇÃO DA VERSÃO 1 (Projeto do Curso Udemy - link do curso [AQUI](https://www.udemy.com/course/spring-boot-mvc-com-thymeleaf/ "Link do curso"))

Foi construído durante 8,5 horas de curso distribuídas em 80 aulas, uma aplicação com template engine Thymeleaf (padrão MVC) e todo código-fonte presente no curso está na branch MASTER. A branch foi mantida fiel ao curso para consulta e demonstração.
Basicamente o projeto do curso é um CRUD web de departamentos, cargos e funcionários e todos seus relacionamentos e as tecnologias utilizadas na versão 1 foram;

## BACKEND
-	Java 11
-	Padrão MVC
-	Spring Boot
-	JPA/Hibernate
-	Maven
-	Banco de dados MYSQL

## FRONTEND
-	HTML/CSS
-	Bootstrap 4
-	Webjars
-	Thymeleaf

## DESCRIÇÃO DA VERSÃO 2 (Contribuição para estudo de caso e melhorias)
A versão 2 é praticamente um projeto a parte, com endpoints exclusivos para serem consumidas no formato de API REST e alimentar um frontend totalmente feito em ANGULAR/NODE. As classes de domínio/entidades foram reaproveitadas (foi criado apenas uma classe de domínio para controlar os usuários) e o projeto do curso praticamente não sofreu alterações. Todas novas classes e implementações para a versão 2  foram concentradas em um pacote exclusivo chamado “angular”. Tudo que foi implementado para esta versão 2 está devidamente separado nas outras 2 branchs, na seguinte sequência;

-	Branch ANGULAR-FRONTEND-INTEGRATION: Nesta branch foi implementado todos os endpoints novos e exclusivos e de uma forma diferente (API Rest ao invés do padrão MVC do curso). O intuito desta branch é armazenar todo código-fonte implementado até o ponto em que a versão 2 entregasse exatamente o mesmo que a versão 1. Algumas melhorias foram adicionadas como um tratamento de exceções personalizado.

-	Branch JWT-ANGULAR-FRONTEND-INTEGRATION: com a branch anterior entregando exatamente os dados que eram fornecidos no curso, chegou a hora de aplicar uma melhoria na aplicação, inserindo uma implementação para Autenticação e Autorização utilizando Spring Security e JWT (JSON web token). Com esta branch terminada, agora somente usuários autenticados tem permissão a API rest da versão 2. Para exemplificação, foi adicionado a regra de negócio em que usuários de perfil ADMINISTRADOR podem executar qualquer chamada na API após logado, podendo utilizar todos os verbos HTTP sem restrição (POST, PUT, GET, DELETE). Já perfis CONVIDADO, possuem algumas restrições de conteúdo, eles podem ver listas de Departamentos e Cargos e não podem cadastrar, alterar ou deletar. A parte de funcionários também foi totalmente restringida para usuários CONVIDADO (como citado anteriormente, apenas para demonstração, estudo de caso e funcionalidade). 

Mesmo os endpoints sendo protegidos pelo Spring Security, a versão 1 foi totalmente LIBERADA e seus enpoints estão abertos *(assim como era no curso)* para mera conferência das diferenças. E é exatamente isso que retrata o que foi dito no primeiro parágrafo deste README ... contemplar 02 aplicações distintas, a primeira com template engine/padrão MVC e a segunda em forma de API/rest.

# As tecnologias utilizadas na Versão 2 foram;

## BACKEND
-	Java 11
-	Padrão API Rest
-	Spring Boot
-	Spring Security
-	JWT (JSON Web Token)
-	JPA/Hibernate
-	Maven
-	Banco de dados MYSQL
	
## FRONTEND (LINK PARA REPOSITORIO GITHUB [AQUI](https://github.com/emerson-roots/conversion-thymeleaf-to-angular-project/ "Link para repositorio frontend ANGULAR"))

-	Angular 12
-	Node.js
-	HTML/CSS
-	Bootstrap 4

Vamos ao que interessa, acessar a aplicação na nuvem...
Antes de mais nada, vale ressaltar que ambas aplicações (BACKEND e FRONTEND) estão implantadas no HEROKU de forma gratuita e em 02 projetos separados tanto para a API backend e para o frontend Angular. Isso significa que após 30 minutos sem uso, cada aplicação entra no modo “hibernar”. Para acordar as aplicações levam alguns segundos. Peço um pouco de compreensão e paciência neste momento.
Abaixo será listado os links das aplicações e uma sequência de prints ilustrativos orientando o acesso, principalmente para a versão 2 onde é necessário autenticar.

## App do curso versão 1:
### https://spring-angular-jwt.herokuapp.com

## app/API de contribuição versão 2 e seus endpoints em forma de API rest (sim, é o mesmo link de projeto):
### https://spring-angular-jwt.herokuapp.com
-	/login
-	/deparment
-	/office
-	/employee

## Credenciais de login para testar as permissões:
- *Perfil ADMIN: e-mail: admin@admin | senha: admin*
- *Perfil CONVIDADO: e-mail: convidado@convidado | senha: convidado*

## Privilégios de perfis:
- *ADMIN: Acesso total, podendo listar, deletar, inserir e alterar qualquer coisa*
- *CONVIDADO: Acesso restrito, pode listar Departamentos e Cargos mas não pode cadastrar nem alterar. Em funcionários, a restrição é total.*
	
## Abaixo alguns exemplos de acesso a API rest utilizando um api cliente de testes como o Postman (Foi estipulado um tempo de expiração do token de 10 minutos, porém há um endpoint de refresh token). Caso queira testar, basta chamar o endpoint /refresh_token passando um token ainda válido. Verá que será retornado um token com tempo renovado.

### Exemplo de requisição GET sem usuário logado/autenticado (endpoint: /employee);
![Requisição para endpoint sem Autênticação](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/GET_sem_antenticacao.jpg)

### Exemplo de requisição POST para efetuar login (Perfil CONVIDADO) e o retorno do cabeçalho *AUTHORIZATION* com token (endpoint: /login);
![Requisição Login recebendo token](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/login_convidado_recebendo_token.jpg)

### Exemplo de requisição GET com usuário autenticado para um recurso liberado para perfil CONVIDADO passando o token recebido no login (endpoint: /office);
![Requisição GET autorizado](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/GET_endpoint_autorizado_para_convidado.jpg)

### Exemplo de requisição GET para um recurso não autorizado para o perfil CONVIDADO (endpoint: /employee);
![Requisição GET não autorizado](https://github.com/emerson-roots/assets/blob/master/assets/curso_spring_MVC_thymeleaf/GET_endpoint_restrito_convidado.jpg)

- *Fica o convite para testar os mesmos endpoints com um usuário de perfil ADMINITRADOR*

# Como executar o projeto LOCALMENTE

## Pré-requisitos: 
- Java 11
- MySql Online (não é preciso criar a base de dados, a aplicação cria automaticamente)


```bash
# na linha de comando, clonar repositório
git clone https://github.com/emerson-roots/curso-spring-MVC-thymeleaf

# entrar na pasta do projeto back end

# fazer build no projeto pelo Maven (caso houver testes ativos, comente a anotação @Test ou ignore os testes)
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

