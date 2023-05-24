#  tp1
Trabalho 1 da disciplina TPPE no semestre 2023/1.

## 1. Grupo

| Aluno                              | Matrícula  |
| ---------------------------------- | ---------- |
| Victor Buendia Cruz de Alvim       | 190020601  |
| Arthur Alves de Matos              | 190010495  |
| Klyssmann Henrique Ferreira de Oliveira | 202028202  |
| Caio César Oliveira                | 190085291  |
| Victor de Souza Cabral             | 190038900  |
| João Vitor de Souza Durso          | 180123459  |

## 2. Escopo do trabalho

O trabalho apresenta o emprego das três técnicas de TDD - Test-Driven Development (falsificação, duplicação e triangulação) em, pelo menos, nas três seguintes situações:

- Calculo da completude de campos OR EXCLUSIVO;
- Cálculo da completude de registros multi-campos.
- Também há evidências na sequência dos commits: falsificação; duplicação; e triangulação.

Com base nessas técnicas, elaborou-se um programa que calcula a completude de campos aninhados ou não.


### 2.1. Fonte de dados

Utilizando como base um arquivo json, pode-se analisar os seguintes aspectos:

Campos atômicos:
- `title`
- `publicationDate`
- `language`

Campos aninhados OR exclusivo:
- `Author.identifier.lattes`
- `Author.identifier.orcid`

Campos aninhados OR inclusivo:
- `author.nationality`
- `author.birthCountry`
- `author.birthCity`
- `author.birthState`

Os outros campos do arquivo json que não estão listados são ignorados no teste de completude.

### 2.2. Componentes

O programa consiste nas seguintes classes:

- [`Completude.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/completude/Completude.java): classe que calcula a completude dos campos;
- [`JSONParser.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/parser/JSONParser.java): classe que transforma os dados no formato JSON em objetos dispostos em uma tabela hash;
- [`TesteCompletude.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/teste/TesteCompletude.java): Testes de exceção;
- [`TesteCompletudeFioCruz.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/teste/TesteCompletudeFioCruz.java): Testes com o JSON fornecido conforme o item 2.1;
- [`TesteCompletudeMultiCampos.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/teste/TesteCompletudeMultiCampos.java): Testes de completude de multi-campos;
- [`TesteCompletudeOrExclusivo.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/teste/TesteCompletudeOrExclusivo.java): Testes de completude com campos OR Exclusivo; e
- [`TesteCompletudeOrInclusivo.java`](https://github.com/Victor-Buendia/tp1/blob/main/src/teste/TesteCompletudeOrInclusivo.java): Testes de completude com campos OR Inclusivo.


### 2.3. Rodando os testes

![Testes](https://github.com/Victor-Buendia/tp1/blob/main/asset/testes.JPG)


### 2.4. Configuração de ambiente

Neste trabalho utilizamos o pacote JSON-Java que é uma implementação de referência que demonstra como analisar documentos JSON em objetos Java e também como gerar novos documentos JSON a partir das classes Java. O JAR para ser utilizado no **ClassPath** se encontra na [pasta json deste repositório](https://github.com/Victor-Buendia/tp1/tree/main/org_json). Caso tenha algum problema, basta baixar o JAR através do Maeven [clicando aqui](https://mvnrepository.com/artifact/org.json/json/20230227).

Para os testes, foi utilizado o JUnit4 do próprio Eclipse.

Você deve, após clonar o repositório, configurar o ClassPath no Eclipse para usar o [JAR](https://github.com/Victor-Buendia/tp1/blob/main/org_json/json-20230227.jar). Com o projeto desconfigurado, você receberá o seguinte erro:

![Testes](https://github.com/Victor-Buendia/tp1/blob/main/asset/erro_json.JPG)

Basta baixar o pacote e editar o caminho do arquivo, conforme abaixo:

![Testes](https://github.com/Victor-Buendia/tp1/blob/main/asset/erro_json_corrigido.JPG)
