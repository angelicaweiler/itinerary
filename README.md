Avaliação técnica para Desenvolvedor Backend

Tecnologias utilizadas:

Neste projeto foram utilizadas as seguintes tecnologias:

Java 12
Spring Boot
Maven
JPA
MySQL:5.6
Docker //devido a falta de tempo de execução, não foi possível finalizar o docker.

Os frameworks foram escolhidos em razão de facilitarem a configuração do sistema, principalmente no que se refere ao Spring Boot que possui várias ferramentas que facilitam a execução e conexão com banco de dados.

Como testar a aplicação?
Recomendo utilizar uma ferramenta de teste como Postman.

Parte 1)
Integração com a api datapoa.com.br

1.1) Listar as linhas de ônibus:
endpoint para teste:
Listagem de linhas de ônibus cadastradas [@GET]
http://localhost:8080/bus/import
O comando lista e salva as linhas de ônibus.

1.2) Listar os itinerários de ônibus:
Listar os itinerários de ônibus. Em razão da alta quantidade de dados referente aos itinerários de ônibus, foi criado um método para que essa listagem se dê de acordo com o id das linhas de ônibus.
Método [@GET]
http://localhost:8080/itinerary/import/{idLinha}

Parte 2)Listagem de linhas por nome:
Método que lista as linhas de acordo com o nome passando no parâmetro do endpoint.
http://localhost:8080/bus/{nome} [@GET]

Parte 3) CRUD de Linhas de Itinerários:
Métodos para CRUD de linhas e itinerários no banco de dados:
3.1) Método para update ou save no banco de dados, de acordo com as informações passadas:
http://localhost:8080/bus/register -[@POST]

http://localhost:8080/itinerary/register -[@POST]

3.2) Método para delete no banco de dados de acordo com id:
http://localhost:8080/bus/{id}/delete -[@DELETE]

http://localhost:8080/itinerary/{id}/delete -[@DELETE]

Parte 5) API para criação e consulta de pontos de taxi;
5.1) AddPontosTaxi:
Api para adicionar pontos de taxi localmente. Execute no RUN.
5.2) ListPontosTaxi:
Api para listar pontos de taxi localmente. Execute no RUN.

