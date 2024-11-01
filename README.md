# Projeto Algoritimos de Busca em Ambiente Paralelizados

Este projeto foi desenvolvido em **Java 8** com o uso do **Apache Maven** como gerenciador de dependências e requer o **Apache Tomcat 9** para ser executado. Certifique-se de que todos os pré-requisitos estão instalados corretamente antes de iniciar o projeto.

## Pré-requisitos

- **Java 8**: Certifique-se de que o Java Development Kit (JDK) 8 está instalado e configurado em sua máquina. Verifique a instalação executando o comando:


  java -version

O comando deve retornar uma versão semelhante a 1.8.x.

    Apache Tomcat 9: É necessário ter o Tomcat 9 configurado para o deploy da aplicação. 

    Apache Maven: O Maven é usado para gerenciar dependências e compilar o projeto.

Importando o Projeto

Para importar o projeto em uma IDE como IntelliJ IDEA ou Eclipse, siga os passos abaixo:

    Clone o repositório:
		git clone https://github.com/matheustcolares/substituicao-pagina


Abra sua IDE e selecione a opção Import Project ou Importar Projeto.

Selecione a pasta do projeto clonado e escolha a opção de importação como um projeto Maven.

A IDE deve reconhecer automaticamente o pom.xml e baixar todas as dependências necessárias. Caso isso não aconteça, execute o seguinte comando para baixar as dependências manualmente:


	mvn clean install

Certifique-se de que o Java 8 está selecionado como SDK do projeto.

Configure o Tomcat 9 como servidor na sua IDE ou, se preferir, inicie-o manualmente e faça o deploy da aplicação.

Inicie o servidor e acesse a aplicação através do navegador em:

	http://localhost:8080/nome-da-aplicacao