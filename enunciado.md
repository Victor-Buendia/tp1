UnB - Universidade de Brasilia  
FGA - Faculdade do Gama  
TPPE - Técnicas de Programação para Plataformas Emergentes  


# Adaptação do Enunciado
(para facilitar o entendimento)

### Trabalho Prático 1 - _Test-Driven Development_
---

**Enunciado do trabalho** 

Seja o cenário descrito a seguir: 

Cálculo da completude de informações estruturadas, aninhadas ou não: 

Um sistema de informações deve, dentre várias outras atribuições, calcular o
quão completa é uma informação que ele processa baseado em um modelo
descritivo das informações sob análise. As informações são sempre tratadas
como valores textuais, independentemente se o valor é de fato um texto ou não.
Avaliar a completude, nesse caso, significa verificar a presença ou ausência
de algum valor para o campo correspondente. 

Um registro, i.e. um conjunto de informações científicas agregadas, é composto
de vários campos, podendo cada um desses campos serem atômicos ou compostos em
vários níveis. Exemplos: 
* Atômico: CPF
* Atômico: Matricula
* Atômico: Sexo 
* Atômico: Email
* Composto: Nome
  * Atômico: PrimeiroNome
  * Atômico: NomeMeio
  * Atômico: UltimoNome

Os campos podem ser agrupados para criar novos campos formando uma árvore de
campos para os registros. Essa árvore deve respeitar o modelo de dados sob
análise. Exemplo:
* Composto: PessoaFisica
  * Composto: Nome
    * Atômico: PrimeiroNome
    * Atômico: NomeMeio
    * Atômico: UltimoNome
  * Atômico: CPF
  * Atômico: Matricula
  * Atômico: Sexo 
  * Atômico: Email

Os campos aninhados podem ser agrupados seguindo duas regras lógicas bem
conhecidas: A regra OU EXCLUSIVO e OU INCLUSIVO.

Isso se refere à organização de dados em campos aninhados. Um campo aninhado é um campo que contém outros campos dentro dele. Existem duas regras lógicas que podem ser usadas para agrupar esses campos aninhados:

* A regra OU EXCLUSIVO: isso significa que o campo raiz é considerado completo somente quando um e apenas um dos campos filhos está presente. Ou seja, se mais de um campo filho estiver presente, o campo raiz ainda será considerado incompleto.
* A regra OU INCLUSIVO: isso significa que o campo raiz é considerado preenchido quando pelo menos um campo filho está presente. Isso significa que, se pelo menos um campo filho estiver presente, o campo raiz será considerado completo, mesmo que outros campos filhos estejam faltando.

O caso especial em que nenhum campo filho está presente é considerado como não-preenchido, ou seja, o campo raiz é considerado incompleto.

Finalmente, para que o campo raiz seja considerado completo, todos os campos filhos devem estar presentes. Ou seja, todos os campos filhos devem ser preenchidos para que o campo raiz seja considerado completo.

### Exemplo
>Suponha que estamos criando um formulário de registro de usuário para um site de comércio eletrônico, e queremos coletar informações sobre o endereço do usuário. Para isso, podemos criar um campo "endereço" que contém campos filhos, como "rua", "cidade", "estado" e "CEP".
>
>A regra OU EXCLUSIVO seria aplicada se, por exemplo, quiséssemos coletar informações de endereço alternativas. Nesse caso, poderíamos adicionar um segundo campo "endereço alternativo" com os mesmos campos filhos: "rua", "cidade", "estado" e "CEP". Aqui, o usuário precisaria preencher apenas um dos dois campos "endereço" ou "endereço alternativo", pois apenas um dos campos filhos pode estar presente.
>
>A regra OU INCLUSIVO seria aplicada se quiséssemos permitir que o usuário inserisse apenas uma parte do endereço. Nesse caso, o campo "endereço" seria considerado preenchido se, por exemplo, apenas o campo "cidade" fosse preenchido. Isso ocorre porque pelo menos um campo filho está presente.
>
>Por fim, para que o campo "endereço" seja considerado completo, todos os campos filhos, "rua", "cidade", "estado" e "CEP", devem estar presentes e preenchidos. Somente quando todos esses campos forem preenchidos, o campo "endereço" será considerado completo.
>
> "Se o OU EXCLUSIVO fosse empregado no exemplo, seria possível ter o endereço completo (todos os filhos preenchidos)?"
>
> Não, se aplicarmos a regra OU EXCLUSIVO no exemplo, seria impossível ter o endereço completo (todos os filhos preenchidos) para ambos os campos "endereço" e "endereço alternativo". Isso ocorre porque a regra OU EXCLUSIVO exige que apenas um dos campos filhos esteja presente.
>
> No entanto, se quisermos permitir que o usuário preencha todas as informações do endereço em ambos os campos "endereço" e "endereço alternativo", precisamos aplicar a regra OU INCLUSIVO em vez da regra OU EXCLUSIVO. Dessa forma, o campo "endereço" e o campo "endereço alternativo" seriam considerados preenchidos se pelo menos um dos campos filhos estivesse presente, mas ambos os campos seriam necessários para o endereço completo (todos os filhos preenchidos).

Para o caso de campo atômico, ele é considerado completo se há um valor
atribuído a ele. 

O cálculo da completude de um registro é feito de maneira recursiva, de modo
que o valor da completudo do campo pai de um registro é dado pelo valor da
completude de seus filhos, considerando as regras apresentadas anteriormente. 



O trabalho deverá apresentar o emprego das três técnicas de TDD (falsificação,
duplicação e triangulação) em, pelo menos, nas três seguintes situações: 

* Calculo da completude de campos OR EXCLUSIVO; 
* Cálculo da completude de registros multi-campos.

Nesses dois casos deverá, para efeito de evidência, ter a seguinte sequencia de
_commits_:
* falsificação; 
* duplicação; 
* triangulação.


:exclamation::exclamation::exclamation:**ATENÇÃO:** todos os testes finais
deverão estar triangulados por parametrização, com exceção dos testes de
exceções.

---

**Grupos de alunos**

Os trabalhos deverão ser executados por grupos de até 6 alunos. O grupo deverá
criar um repositório público no GitHub, cujo arquivo README.md deverá conter os
nomes e matrículas dos componentes, e as instruções para execução do projeto.

**Entrega do trabalho**

Os trabalhos deverão ser entregues através de repositório no GitHub, até as
23:59:59hs do dia 22 de maio de 2023, impreterivelmente. 

Os grupos deverão informar ao professor o endereço do repositório do projeto
antes da data final de entrega. 

**Valor do trabalho**

30 pontos. 

**Forma de avaliação**

Os trabalhos serão avaliados através:

- da corretude dos cálculos realizados pelo simulador;
- da utilização de testes parametrizados em todos os testes funcionais; 
- da utilização de testes de exceção e, por fim, 
- das seqüências de _commits_ para os casos em que o emprego das técnicas de
  falsificação, duplicação e triangulação é obrigatório.