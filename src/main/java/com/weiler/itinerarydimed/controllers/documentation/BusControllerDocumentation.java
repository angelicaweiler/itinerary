package com.weiler.itinerarydimed.controllers.documentation;

import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.entities.Itinerary;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(
        description = "Serviço para busca de Linhas de Ônibus"
        )
public interface BusControllerDocumentation {

    @ApiOperation(value = "Buscar os dados externos de API referentes a Linhas de Ônibus, importar e salvar os dados em banco de dados ", tags = "Bus", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso ao consultar e salvar os dados em Banco de Dados " , response = Bus.class),
            @ApiResponse(code = 500, message = "Erro ao consultar ou salvar os dados em Banco de Dados", response = ResponseEntity.class)
    })
    ResponseEntity<?> importBus() throws Exception;

    @ApiOperation(value = "Busca linha de ônibus por nome e retorna", tags = "Bus", httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso ao consultar as informações " , response = Bus.class),
            @ApiResponse(code = 500, message = "Erro ao consultar os dados em Banco de Dados", response = ResponseEntity.class)
    })
    ResponseEntity<?> getBusByName(@PathVariable("nome") String nome) throws Exception;

    @ApiOperation(value = "Salva ou atualiza os dados informados pelo usuario, conforme informações do Banco de Dados", tags = "Bus", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso cadastrar ou atualizar as informações " , response = Bus.class),
            @ApiResponse(code = 500, message = "Erro ao cadastrar ou atualizar as informações", response = ResponseEntity.class)
    })
    ResponseEntity<?> registerOrUpdate(@RequestBody Bus bus);

    @ApiOperation(value = "Delete os dados cadastrados por id", tags = "Bus", httpMethod = "DELETE")
    @ApiResponses({@ApiResponse(code = 200, message = "Sucesso deletar os dados" , response = Bus.class),
            @ApiResponse(code = 500, message = "Erro ao deletar os dados", response = ResponseEntity.class)
    })
    ResponseEntity delete(Bus bus);
}
