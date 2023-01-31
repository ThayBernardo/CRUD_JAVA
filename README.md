<h1 align="center">Controle de Funcionários</h1>
<p align="center">A aplicação é um CRUD, onde podemos criar, ler, atualizar e deletar informações do banco de dados.</p>

## 💻 Ferramentas

- Java
- Docker
- Postgres

Para desenvolver a aplicação foram seguidos alguns passos, primeiramente foi feito a conexão com o banco de dados no arquivo <strong>/src/main/java/ControlEmployees/infra/ConnectionFactory.java</strong>, junto adicionando dependência do postgres no arquivo pom.xml para conclusão da conexão. Logo foram feitas as classes Pessoa(Person) e Funcionario(Employee), juntamente com seus getters e setters, classe Pessoa com atributos nome e data de nascimento, classe Funcionário com atributos salário e função, além da classe Funcionário extender da classe Pessoa.

Foi criado um método onde possa inserir novo funcionário na tabela, seguindo os atributos necessários(nome, data de nascimento, salário e função)

Foi criado um método onde possa remover algum funcionário a partir de seu nome.

Foi criado um método onde retorna todos os dados da tabela funcionários, porém com algumas modificações, data convertida para o formato "dd/mm/aaaa", salário com separador de milhar como ponto e decimal como vírgula.

Método onde acontece atualização em todos salários, o método pega o valor do salário de cada funcionário e soma com 10% deste valor, fazendo assim um aumento de salário para todos funcionários de 10%.

Agrupamento de funcionários por função em um MAP, o método cria chave com cada função e adiciona a esta chave os funcionários que tem aquela função, com impressão desses funcionários agrupados.

Foi criado um método que imprime pessoas que fazem aniversário nos meses 10 e 12.

Método onde imprime o funcionário com maior idade, exibindo seu nome e sua idade.

Método no qual imprime lista de funcionários em ordem alfabética.

Método no qual imprime o total de todos salários dos funcionários juntos.

Método no qual imprime quantos salários mínimos(com valor fixo de 1212.00) cada funcionário recebe.

## Como rodar


- Foi criada uma imagem do postgres no docker.

```bash
docker run -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```

- Foi utilizado o pgAdmin4 na próxima etapa.
- Foi criado um server funcionarios e logo após a criação da tabela.
- Crie a tabela na seguinte estrutura.
⚠️ O nome da database deve ser </strong>funcionarios</strong>.

```bash
CREATE TABLE funcionarios (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(50),
  data_nascimento DATE,
  salario DECIMAL,
  funcao VARCHAR(50)
)
```

- Logo após clone o repositório em sua máquina.

```bash
git clone git@github.com:ThayBernardo/CRUD_JAVA.git
```

- Entre na pasta clonada em seu editor de preferência. ⚠️ O projeto foi desenvolvido no IntelliJ.
- Agora seja feliz e utilize as funcionalidades já implementadas para manipular seu banco.

## 📋 Features

|                                    | 🔰   Concluídos    |
| --------------------------         | :----------------: |
| Inserir dados                      |         ✔️         |
| Remover a partir do nome           |         ✔️         |
| Imprimir funcionários              |         ✔️         |
| Atualizar salário em 10%           |         ✔️         |
| Imprimir função e funcionários     |         ✔️         |
| Imprimir aniversáriantes           |         ✔️         |
| Imprimir funcionário maior idade   |         ✔️         |
| Imprimir ordem alfabética          |         ✔️         |
| Imprimir total dos salários        |         ✔️         |
| Imprimir quantidade salário min    |         ✔️         |

## ✍️ Banco de Dados

Para iniciar o banco de Dados foi utilizada uma imagem no docker do Postgres.
A tabela funcionarios no banco de dados tinha colunas:
- nome - Informar nome do funcionário
- data_nascimento - Informar data de nascimento do funcionário
- salario - Informar salário que funcionário recebe
- funcao - Informar o cargo do funcionário

![WhatsApp Image 2023-01-30 at 19 53 01](https://user-images.githubusercontent.com/91172181/215650011-fb17d4f9-b9bd-4870-aff6-4875864e0ecf.jpeg)

## ✍️ Tabela no Banco de Dados

![Captura de tela de 2023-01-30 23-24-32](https://user-images.githubusercontent.com/91172181/215650053-d96fd076-c10b-420f-b269-3920dd166787.png)

## ✍️ Tabela com formatação de data e salário, impressão no console

- nome - String
- data_nascimento - LocalDate
- salario - BigDecimal
- funcao - String

![Captura de tela de 2023-01-30 23-27-14](https://user-images.githubusercontent.com/91172181/215650107-8ae55414-55a4-4605-b883-6b0530453991.png)

## 📢 Adicionais
- Alguns métodos estão implementados também com querys para decisão da pessoa que está utilizando decidir qual sua preferência, os métodos estão implementados no arquivo <strong>/src/main/java/ControlEmployees/dao/EmployeeDAO.java</strong>
