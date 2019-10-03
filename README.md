# BackendTest

Para rodar o programa do Windows utilize o script.bat, para rodar no Linux use o script.sh (os scripts utilizam o Maven para rodar).

A solução que adotei foi pautada na preocupação de percorrer os dados a menor quantidade possível. Para isso realizei a leitura do arquivo (account_transactions.log) em seguida do JSON completo (db.json, que possui os pagamentos e os recebimentos) e condensei em uma única lista (já com as transações ordenadas ascendetemente por data). Então, para gerar os relatórios criei um objeto para cada um e, conforme os dados vão sendo iterados, esses objetos vão construindo o relatório que são responsáveis, de modo que percorro a lista com os dados com o intuito de gerar os relatórios apenas uma vez. 
Assim, após gerar os relatórios e os imprimo de uma vez
