# BackendTest

### Como executar o programa
Para rodar o programa do Windows utilize o script.bat, para rodar no Linux use o script.sh (os scripts utilizam o Maven para rodar).

### Raciocínio utilizado para a solução
A solução que adotei foi pautada na preocupação de percorrer os dados a menor quantidade possível. Para isso enquanto vou lendo o arquivo ou json os relatórios já vão sendo concebidos também. Para gerar os relatórios criei um objeto responsável por gerar as informações de cada um e, conforme os dados vão sendo iterados, esses objetos vão construindo o relatório de sua resbonsabilidade, de modo que percorro a lista de dados com o intuito de gerar os relatórios apenas uma vez. 
Os objetos também são responsáveis por imprimir seu próprio relatório, sendo assim simples a impressão bastando executar a função que realiza a impressão de cada um ao final do processo.
