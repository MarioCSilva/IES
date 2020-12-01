# Lab 2

## Authors
 - **[Mário Silva (nmec 93430)](https://github.com/MarioCSilva)**

## Server side programming with servlet
"Java Servlet is the foundation web specification in the Java Enterprise environment. A Servlet is a Java
class that runs at the server, handles (client) requests, processes them, and reply with a response. A
servlet must be deployed into a (multithreaded) Servlet Container in order to become usable.
Containers1 handle multiple requests concurrently. Servlet is a generic interface and the HttpServlet is
an extension of that interface (the most used type of Servlets).
When an application running in a web server receives a request, the Server hands the request to the
Servlet Container which in turn passes it to the target Servlet.
Servlet Container is a part of the usual set of services that we can find in Java Application Server."

# 2.1 Server-side programming with servlets
## Instalação do Tomcat, depois de descarregado o ficheiro binário, dentro da pasta bin:
`$ chmod +x catalina.sh`

## Iniciar o Tomcat:
`$ sh ./startup.sh`

## Para confirmar que o servidor Tomcat está a correr foi executado o comando:
`$ curl -I 127.0.0.1:8080`

    HTTP/1.1 200 
    Content-Type: text/html;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Tue, 20 Oct 2020 08:28:03 GMT

## Tomcat ambiente de gestão
link: (http://localhost:8080/manager)

## Registar os users e as suas roles:
No ficheiro ´conf/tomcat-users.xml´ adicionar:

    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="admin" password="admin" roles="manager-gui, manager-script"/>

## Exemplos de Servlet do Tomcat
Nos exemplos vistos a class principal extende sempre `HtttpServlet`.

## Projeto Maven
Foi criado um projeto Maven com os seguintes archetypes:

    archetypeGroupId=org.codehaus.mojo.archetypes
    archetypeArtifactId=webapp-javaee7
    archetypeVersion=1.1

## Fazer deploy de uma aplicação para o Tomcat:
Através da interface do Tomcat na página http://localhost:8080/manager fazer o deploy do ficheiro .war para o servidor.
Verificar através do link http://localhost:8080/webapp-javaee7-1.1/ que foi bem sucedido.
Ou também através do comando dentro da pasta do apache Tomcat:

    $ tail logs/catalina.out

Verificamos o output:

    20-Oct-2020 09:52:29.652 INFO [http-nio-8080-exec-17] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [/Users/mario/Desktop/IES/Lab2_93430/lab2.1/apache-tomcat-9.0.39/webapps/webapp-javaee7-1.1.war] has finished in [41] ms

## Integrar suporte ao Tomcat ao IDE (VisualStudio)
Primeiro o servidor deve ser terminado:

    $ sh ./shutdown.sh

Depois adicionar o servidor ao VScode, e fazer debug do ficheiro .war da aplicação.

## Servlet
Fonte: https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/#webservlet_annotation

## Adicionar um servlet ao servidor
Adicionar um ficheiro java à aplicação, compilar o projeto maven e fazer deploy do ficheiro .war.

## Lidar com Servlet Request
Para ler o parâmetro "username" passado pelo url da seguinte maneira:

    http://localhost:8080/webapp-javaee7-1.1/Servlet?username=Admin

Basta aceder ao objeto HttpServletRequest e ir buscar o parâmetro da seguinte maneira:
`request.getParameter("username")`

## Fazer deploy da app do Tomcat num docker container
Clonar a imagem tomcat:

    docker image pull tomcat:8.0

Criar e começar um container a partir da imagem:

    docker container create --publish 8088:8080 --name my-tomcat-container tomcat:8.0
    docker container start my-tomcat-container

A aplicação Tomcat pode ser acedida em http://localhost:8088.

Para fazer deploy da nossa app no container é preciso primeiro criar um ficheiro Dockerfile na raíz da app.
Depois fazer build de docker image file a partir do ficheiro Dockerfile criado.

    docker image build -t your_name/some-app-image ./
    
    docker container run -it --publish 8088:8080 your_name/some-app-image

## An excerpt from the application server log, highlighting the successful deployment of the application:
    20-Oct-2020 09:52:29.612 INFO [http-nio-8080-exec-17] org.apache.catalina.startup.HostConfig.deployWAR Deploying web application archive [/Users/mario/Desktop/IES/Lab2_93430/lab2.1/apache-tomcat-9.0.39/webapps/webapp-javaee7-1.1.war]
    
    20-Oct-2020 09:52:29.652 INFO [http-nio-8080-exec-17] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [/Users/mario/Desktop/IES/Lab2_93430/lab2.1/apache-tomcat-9.0.39/webapps/webapp-javaee7-1.1.war] has finished in [41] ms

## What are the responsibilities/services of a “servlet container”?
_A web container is responsible for managing the lifecycle of servlets, mapping a URL to a particular servlet and ensuring that the URL requester has the correct access-rights, it also handles requests to servlets, Jakarta Server Pages (JSP) files, and other types of files that include server-side code. The Web container creates servlet instances, loads and unloads servlets, creates and manages request and response objects, and performs other servlet-management tasks._ https://en.wikipedia.org/wiki/Web_container


# 2.2
## Server-side programming with embedded servers
_"Jetty can be used in standalone mode, but the main purpose behind building jetty was so that it could be used inside an application instead of deploying an application on jetty server. Generally you write a web application and build that in a WAR file and deploy WAR file on jetty server. In Embedded Jetty, you write a web application and instantiate jetty server in the same code base."_
https://examples.javacodegeeks.com/enterprise-java/jetty/embedded-jetty-server-example/


# 2.3
## Introduction to web apps with Spring Boot
Criar um projeto maven com Spring Web dependency através do Spring Initializr. (https://start.spring.io/)
Para correr o projeto no porto default 8080 basta usar um destes comandos:

    $ mvn install -DskipTests && java -jar target\webapp1-0.0.1-SNAPSHOT.jar
    $ ./mvnw spring-boot:run

The Spring Boot Maven plugin provides many convenient features:
- It collects all the jars on the classpath and builds a single, runnable "über-jar", which makes it more convenient to execute and transport your service.
- It searches for the public static void main() method to flag as a runnable class.
- It provides a built-in dependency resolver that sets the version number to match Spring Boot dependencies. You can override any version you wish, but it will default to Boot’s chosen set of versions.

## Create a Web Controller
In Spring’s approach to building web sites, HTTP requests are handled by a controller. You can easily identify the controller by the @Controller annotation. In the following example, GreetingController handles GET requests for /greeting by returning the name of a View (in this case, greeting). A View is responsible for rendering the HTML content. The following listing (from src/main/java/com/example/servingwebcontent/GreetingController.java) shows the controller:

É criado um controller que responde ao caminho "/greeting" e responde ao pedido enviando a pagina greetingpage com o devido tratamento dos parametros.

    @Controller
    public class GreetingController {
        @GetMapping("/greeting")
        public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
            model.addAttribute("name", name);
            return "greeting";
        }
    }

The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.

@RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method. This query string parameter is not required. If it is absent in the request, the defaultValue of World is used. The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.

The implementation of the method body relies on a view technology (in this case, Thymeleaf) to perform server-side rendering of the HTML. Thymeleaf parses the greeting.html template and evaluates the th:text expression to render the value of the ${name} parameter that was set in the controller.

A common feature of developing web apps is coding a change, restarting your app, and refreshing the browser to view the change. This entire process can eat up a lot of time. To speed up the cycle of things, Spring Boot comes with a handy module known as spring-boot-devtools.
- Enable hot swapping
- Switches template engines to disable caching
- Enables LiveReload to refresh browser automatically
- Other reasonable defaults based on development instead of production

## Create a Resource Controller
A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object. The object data will be written directly to the HTTP response as JSON.

## Test the Service
Usar o comando:

    curl -o out.json http://localhost:8080/greetingRest

Conteúdo do ficheiro out.json:

    {"id":1,"content":"Hello, World!"}% 

Visitar http://localhost:8080/greeting:

    {"id":2,"content":"Hello, World!"}

Passar um parâmetro `name` no endereço http://localhost:8080/greeting?name=User:

    {"id":3,"content":"Hello, User!"}

This change demonstrates that the @RequestParam arrangement in GreetingController is working as expected. The name parameter has been given a default value of World but can be explicitly overridden through the query string.

Notice also how the id attribute has changed from 1 to 2 to 3. This proves that you are working against the same GreetingController instance across multiple requests and that its counter field is being incremented on each call as expected.


## Web applications in Java can be deployed to stand-alone applications servers or embedded servers. Elaborate on when to choose one over the other (and relate with Spring Boot).
Embedded application servers run inside the same process space as your application. Your application is responsible for starting the server and is also responsible for configuring the server.

Embedded Application Servers should be used when the goal is to create a self-contained application, when it's not necessary or worth it extra installations or configurations of an app server and when an exception is caught on the application the server is suppost to continue running normally.

In contrast, a Standalone application server runs separately from your application. The server is configured using separate config files, forwards requests to your application and might be responsible for loading your application.

Standalone servers should be used when the goal is to create a flexible application that is easy to switch servers, application errors shouldn't stop the server, deploy app updates without restarting the server and when it's necessary and worth the extra complexity to maintain and deploy a server individually from the application.

SpringBoot has been the most used tool for standalone applications, it's not necessary to deploy the application to a web server or any special environment because Spring Boot takes care of that starting and configuring an embedded web server and deploys your application there.

## Give specific examples of annotations in Spring Boot that implement the principle of convention-over-configuration.
Spring has always favoured convention over configuration, which means it takes up the majority of working uses cases into consideration and goes by it rather than nit-picking an exact configuration and dependencies required for a specific application development. 

_"if you establish a set of naming conventions and suchlike, you can substantially cut down on the amount of configuration that is required to set up handler mappings, view resolvers, ModelAndView instances, etc_"

Some Spring Boot annotations that use this principle:
- @Bean - indicates that a method produces a bean to be managed by Spring.
- @Service - indicates that an annotated class is a service class.
- @Repository - indicates that an annotated class is a repository, which is an - abstraction of data access and storage.
- @Configuration - indicates that a class is a configuration class that may contain bean definitions.
- @Controller - marks the class as web controller, capable of handling the requests.
- @RequestMapping - maps HTTP request with a path to a controller method.
- @Autowired - marks a constructor, field, or setter method to be autowired by Spring - dependency injection.
- @SpringBootApplication - enables Spring Boot autoconfiguration and component scanning.

## Which annotations are transitively included in the @SpringBootApplication?
Many Spring Boot developers like their apps to use auto-configuration, component scan and be able to define extra configuration on their "application class". A single @SpringBootApplication annotation can be used to enable those three features, that is:

    @EnableAutoConfiguration: enable Spring Boot’s auto-configuration mechanism
    
    @ComponentScan: enable @Component scan on the package where the application is located (see the best practices)
    
    @Configuration: allow to register extra beans in the context or import additional configuration classes