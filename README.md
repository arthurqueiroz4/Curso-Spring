# Curso-Spring

Este é um projeto que contém um curso introdutório de Spring Framework. O curso é composto por vários módulos e cada um deles aborda um aspecto diferente do Spring.

Este curso é destinado a desenvolvedores que desejam aprender a trabalhar com o Spring Framework, um dos frameworks mais populares para desenvolvimento de aplicativos Java.

## Requisitos

Para executar este projeto, é necessário ter instalado:

- JDK 11 ou superior
- Maven 3.x ou superior
- Git

## Executando o Projeto

Para executar este projeto, siga as instruções abaixo:

1. Faça o clone deste repositório:
- ```git clone https://github.com/arthurqueiroz4/Curso-Spring.git```
2. Acesse o diretório do projeto:
- ```cd Curso-Spring```
3. Compile o projeto:
- ```mvn clean install```
5. Execute o projeto:
- ```mvn spring-boot:run```
Isso iniciará o servidor localmente e você poderá acessar o aplicativo no navegador em http://localhost:8080.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:
```Curso-Spring/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── exemplo/
│   │   │           ├── config/
│   │   │           │   ├── AppConfig.java
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller/
│   │   │           │   └── HomeController.java
│   │   │           ├── model/
│   │   │           │   └── Usuario.java
│   │   │           ├── repository/
│   │   │           │   └── UsuarioRepository.java
│   │   │           └── service/
│   │   │               ├── UsuarioService.java
│   │   │               └── UsuarioServiceImpl.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── estilo.css
│   │       ├── templates/
│   │       │   ├── home.html
│   │       │   └── usuarios.html
│   │       ├── application.properties
│   │       ├── banner.txt
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── com/
│               └── exemplo/
│                   ├── CursoSpringApplicationTests.java
│                   └── UsuarioRepositoryTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```


Contato

Se você tiver alguma dúvida ou sugestão sobre este projeto, sinta-se à vontade para entrar em contato comigo pelo e-mail arthurqueiroz4@gmail.com.
