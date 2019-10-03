# BackendTest

### Como executar o programa
Para rodar o programa do Windows utilize o script.bat, para rodar no Linux use o script.sh (os scripts utilizam o Maven para rodar).

### Raciocínio utilizado para a solução
A solução que adotei foi pautada na preocupação de percorrer os dados a menor quantidade possível. Para isso realizei a leitura do arquivo (account_transactions.log) em seguida do JSON completo (db.json, que possui os pagamentos e os recebimentos) e condensei em uma única lista (já com as transações ordenadas ascendetemente por data). Então, para gerar os relatórios criei um objeto responsável por gerar as informações de cada um e, conforme os dados vão sendo iterados, esses objetos vão construindo o relatório de sua resbonsabilidade, de modo que percorro a lista de dados com o intuito de gerar os relatórios apenas uma vez. 
Os objetos também são responsáveis por imprimir seu próprio relatório, sendo assim simples a impressão bastando executar a função que realiza a impressão de cada um ao final do processo.
