# IES Lab1
## Authors
 - **[Mário Silva (nmec 93430)](https://github.com/MarioCSilva)**

## Lab1.1

## Maven Tutorial

### Verificar a versão do Maven:
`$ mvn --version`

### Criar o projeto 'my_app':
`$ mvn archetype:generate -DgroupId=MyWeatherRadar -DartifactId=weather-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4  -DinteractiveMode=false`

### Descrição:
 - O diretório `src/main/java` contém o código source, o `src/test/java` contém o código de teste, e o ficheiro `pom.xml` é o Project Object Model, or POM do projeto.

### Fazer Build ao projeto:
`$ mvn package`

### Correr o projeto:
`$ java -cp target/weather-app-1.0-SNAPSHOT.jar MyWeatherRadar.App`


**Naming Conventions:** [https://maven.apache.org/guides/mini/guide-naming-conventions.html](https://maven.apache.org/guides/mini/guide-naming-conventions.html)

## O que é um Archetype?
Um archetype é o plugin que fornece o objetivo do comando que cria um projeto simples com base nesse objetivo.

## O que é um groupID?
O groupID é o nome do projeto atual que identifica-o facilmente em relação aos outros projetos existentes.

## O que é um artifactID?
O artifactID é o nome do jar e do sub-diretório que são criados. É também chamado ao output de um processo de build um artifact.

### Maven Build LifeCycle
These are the most common default lifecycle phases executed:

-   **validate**: validate the project is correct and all necessary information is available
-   **compile**: compile the source code of the project
-   **test**: test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
-   **package**: take the compiled code and package it in its distributable format, such as a JAR.
-   **integration-test**: process and deploy the package if necessary into an environment where integration tests can be run
-   **verify**: run any checks to verify the package is valid and meets quality criteria
-   **install**: install the package into the local repository, for use as a dependency in other projects locally
-   **deploy**: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.

There are two other Maven lifecycles of note beyond the _default_ list above. They are

-   **clean**: cleans up artifacts created by prior builds (`mvn clean`)

-   **site**: generates site documentation for this project (`mvn site`)

Phases are actually mapped to underlying goals. The specific goals executed per phase is dependant upon the packaging type of the project. For example, _package_ executes _jar:jar_ if the project type is a JAR, and _war:war_ if the project type is - you guessed it - a WAR.

An interesting thing to note is that phases and goals may be executed in sequence.

`$ mvn clean dependency:copy-dependencies package`

This command will clean the project, copy dependencies, and package the project (executing all phases up to _package_, of course).

#### Gerar um site:
`$ mvn site`
Esta fase gera um site baseado na informação do POM do projeto. É possível ver a documentação em `target/site`.

## Preparar um novo projeto MyWeatherRadar:
- Especificar a versão 11 do compilador no ficheiro pom.xml.
- Configurar dependências: Biblioteca Retrofit para conseguir fazer pedidos GET à API do IPMA, Biblioteca GSON para fazer um parsing ao JSON recebido da chamada à API.

## Correr o projeto com as alterações finais:
`$ mvn exec:java -Dexec.mainClass="MyWeatherRadar.App"` ou `mvn exec:java -Dexec.mainClass="MyWeatherRadar.App" -Dexec.args=1010500`

### O que é um **Maven Goal?**
Uma fase é constituída por vários objetivos/tarefas a cumprir. Estes objetivos são os chamamos GOALS. Representam uma específica tarefa que contribui para a construção e gestão do projeto. Por vezes não estão ligados a uma fase.
Os plugins disponibilizam um ou mais GOALS.

### Quais os principais "Maven Goals" e a respetiva sequência de invocação?
Os principais GOALS são:
- compile (compiler:compile),
- test (surefire:test),
- package (jar:jar),
- install (install:install),
- deploy (deploy:deploy),
- process-resources (resources:resource),
- process-test-resources (resources:testResources),
- test-compile (compiler:testCompile).

A síntaxe de execução de um GOAL é:
$ mvn plugin-prefix:goal
$ mvn plugin-group-id:plugin-artifact-id[:plugin-version]:goal


## Lab1.2

## Repositório criado:
https://github.com/MarioCSilva/IES_lab1_MyWeatherApp

## Criar um repositório local e fazer mudanças:
`$ cd MyWeatherRadar` - mudar para o diretório que se pretende
`$ git init` - iniciar um repositório local
`$ git remote add origin https://github.com/MarioCSilva/IES_lab1_MyWeatherApp.git` - repositório criado inicialmente no GitHub
`$ git add .` - marca todas as mudanças para serem "commited"
`$ git commit -m "Initial commit"` - cria um commit
`$ git push -u origin master` - efetua o commit para o repositório

## Trabalhar em branches:
`$ git checkout -b branch-name` - cria uma branch
`$ git checkout branch-name` - muda para a branch pretendida
`$ git status` - verificar qual branch está selecionada atualmente e outros dados
`$ git push -u origin branch-name` - faz push das mudanças para a branch

## Registo das operações git realizadas no repositório:
`$ git log --graph --oneline --decorate`

## Correr o projeto com as alterações finais:
`$ mvn exec:java -Dexec.mainClass="MyWeatherRadar.App"` ou `mvn exec:java -Dexec.mainClass="MyWeatherRadar.App" -Dexec.args=Aveiro`

## Lab1.3

## Docker
"A utilização de containers (juntamente com ficheiros que contém as instruções para preparar um ambiente de execução de uma solução) facilita a vida do developer (é mais fácil preparar e partilhar um ambiente) e da produção (“operations”)."

### DockerFile:
"Dockerfiles descrevem como montar um sistema de arquivos privado para um container, e também pode conter alguns metadados que descrevem como executar um container baseado numa imagem."

### Criar um grupo docker:
`$ groupadd docker`

### Adicionar utilizador ao grupo:
`$ usermod -aG docker $USER`

### Ativar as mudanças do grupo:
`$ newgrp docker`

### Correr a imagem Hello-World:
`$ docker run [OPTIONS] hello-world`
OPTIONS:
--rm 		Remover o container automaticamente quando sai
-P, --publish 	Publicar todos os ports	

### Listar as imagens:
`$ docker image ls`

### Listar containers:
`$ docker container ls`

### Listar todos os processos ativos:
`$ docker ps`

### Parar container de correr:
`$ docker stop bb`

### Eliminar container:
`$ docker rm --force bb`


## Portainer running within Docker
(https://www.portainer.io/installation/)
"Embora toda a gestão dos containers possa ser feita na linha de comandos, por vezes é mais fácil usar um ambiente gráfico. O portainer é uma aplicação web que permite aceder, no browser, ao ambiente de gestão do Docker."


## Servidor PostgreSQL
### Construir uma imagem a partir do Dockerfile e dar-lhe um nome:
`$ docker build -t NAME .`
`$ docker build -t eg_postgresql .`

### Correr o servidor container PostgreSQL em background:
`$ docker run --rm -P --name pg_test eg_postgresql`

### Fazer container linkingg:
`$ docker run --rm -t -i --link pg_test:pg eg_postgresql bash postgres@7ef98b1b7243:/$ psql -h @PG_PORT_5432_TCP_ADDR -p $PG_PORT_5432_TCP_PORT -d docker -U docker --password`

### Conectar:
`$ psql -h localhost -p 49153 -d docker -U docker --password`

### A área de dados (da base de dados) deve persistir entre reboots (a base de dados não deve ser volátil):
Para conseguir persistir os dados da base de dados, teríamos de criar um volume e fazer link a esta imagem, usar docker-compose por exemplo. Também é possível fazer recorrendo a um portainer. Uma outra opção para utilizar volumes será, seguindo a configuração atual da dockerfile existente, recorrer à flag -v, ao correr o container:

`$ docker run --rm -v pgdata:/var/lib/postgresql/9.3/main -P --name pg_test eg_postgresql``

### Exemplo de como popular a base de dados:
```
$ docker=# CREATE TABLE cities (
docker(#     name            varchar(80),
docker(#     location        point
docker(# );
CREATE TABLE
$ docker=# INSERT INTO cities VALUES ('San Francisco', '(-194.0, 53.0)');
INSERT 0 1
$ docker=# select * from cities;
     name      | location
---------------+-----------
 San Francisco | (-194,53)
(1 row)
```


### Usar um volume para inspecionar os ficheiros log do PostgreSQL e para fazer backup da configuração e dos dados:
`$ docker run --rm --volume-from pg_test -t -i busybox sh`

### Docker Volumes
Guardam os dados e podem ser facilmente partilhados entre containers.

### Outros comandos do Docker Compose:
### Começar aplicação:
`$ docker-compose up`

### Começar aplicação em detached mode:
`$ docker-compose up -d`

### Verificar que serviços estão a correr:
`$ docker-compose ps`

### Ver outros comandos:
`$ docker-compose --help`

### Parar todos os serviços:
`$ docker-compose stop`

### Remover todos os containers e até a data dos volumes:
`$ docker-compose down --volumes`


### Resultado do comando: `$ docker container ls --all`:
```
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS                      PORTS                    NAMES
25b3c5242c49        composetest_web          "flask run"              5 minutes ago       Up 5 minutes                0.0.0.0:5000->5000/tcp   composetest_web_1
e636c75b9947        postgres:11              "docker-entrypoint.s…"   5 days ago          Exited (1) 17 minutes ago                            my_postgres
c3d748b95335        redis:alpine             "docker-entrypoint.s…"   5 days ago          Up 5 minutes                6379/tcp                 composetest_redis_1
296d156e6f5c        portainer/portainer-ce   "/portainer"             5 days ago          Exited (2) 24 minutes ago                            portainer
4444320f2959        hello-world              "/hello"                 5 days ago          Exited (0) 5 days ago                                mystifying_feynman
```
Este comando permite mostrar todos os containers docker criados, assim como os seu ID, data de criação, data de paragem do container, e nome associado.

### Qual a relevância de configurar “volumes” quando se pretende preparar um container para servir uma base de dados?
Permite que os dados continuem a existir depois de a máquina ou serviço que os criou for encerrado.
Também permite que a informação seja transferida para outro computador host intacta.
