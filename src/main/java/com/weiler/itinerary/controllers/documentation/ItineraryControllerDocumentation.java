package com.weiler.itinerary.controllers.documentation;

import com.weiler.itinerary.entities.Bus;
import com.weiler.itinerary.entities.Itinerary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api(
        description = "Serviço para busca de Itinerários de Linhas de Ônibus"
        )
public interface ItineraryControllerDocumentation {


    @ApiOperation(value = "Buscar os dados externos de API referentes a Itinerários de Linhas de Ônibus, importa e salva os dados em banco de dados ", tags = "Itinerary", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso ao consultar e salvar os dados em Banco de Dados " , response = Itinerary.class),
            @ApiResponse(code = 500, message = "Erro ao consultar ou salvar os dados em Banco de Dados/ ID de linha inexistente", response = ResponseEntity.class)
    })
    ResponseEntity<?> importItinerary(@PathVariable("idLinha") Long idLinha) throws Exception;

    @ApiOperation(value = "Salva ou atualiza os dados informados pelo usuario, conforme informações do Banco de Dados", tags = "Itinerary", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso cadastrar ou atualizar as informações " , response = Itinerary.class),
            @ApiResponse(code = 500, message = "Erro ao cadastrar ou atualizar as informações", response = ResponseEntity.class)
    })
    ResponseEntity<?> registerOrUpdate(@RequestBody Itinerary itinerary);

    @ApiOperation(value = "Delete os dados cadastrados por id", tags = "Bus", httpMethod = "DELETE")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso deletar os dados" , response = Bus.class),
            @ApiResponse(code = 500, message = "Erro ao deletar os dados", response = ResponseEntity.class)
    })
    ResponseEntity delete(Itinerary itinerary);


}
