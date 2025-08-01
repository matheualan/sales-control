package com.salescontrol.controller.client;

import com.salescontrol.dto.client.ClientGetDTO;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.dto.client.ClientPutDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressGetDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressPostDTO;
import com.salescontrol.model.Client;
import com.salescontrol.service.ClientService;
import com.salescontrol.util.DateUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final ClientService clientService;
    private final DateUtil dateUtil;

    @ApiResponse(responseCode = "201 - Created", description = "Deve retornar (201 - Created) ao salvar no banco de dados")
    @PostMapping(value = "/save-client")
    public ResponseEntity<ClientPostDTO> saveClient(@RequestBody @Valid ClientPostDTO clientPost) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveClient()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientPost));
    }

    @ApiResponse(responseCode = "201 - Created", description = "Deve retornar (201 - Created) ao salvar no banco de dados")
    @PostMapping(value = "/save-multiple-clients")
    public ResponseEntity<List<ClientPostDTO>> saveMultipleClients(
            @RequestBody List<ClientPostDTO> multipleClients) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveMultipleClients()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveMultipleClients(multipleClients));
    }

    @PostMapping(value = "/save-client-with-address")
    public ResponseEntity<ClientForAddressPostDTO> saveClientWithAddress(
            @RequestBody ClientForAddressPostDTO clientWithAddress) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveClientWithAddress()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClientWithAddress(clientWithAddress));
    }

    @GetMapping(value = "/list-clients-with-addresses")
    public ResponseEntity<List<ClientForAddressGetDTO>> listClientsWithAddresses() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listClientsWithAddresses()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClientsWithAddresses());
    }

    @GetMapping(value = "/list-client-entity")
    public ResponseEntity<List<Client>> listClients() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listClients()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClients());
    }

    @GetMapping(value = "/list-client-dto")
    public ResponseEntity<List<ClientGetDTO>> listClientsDTO() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listClientsDTO()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClientsDTO());
    }

    @GetMapping(value = "/find-client-byName")
    public ResponseEntity<ClientGetDTO> findClientByName(@RequestParam String name) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET findClientByName()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByName(name));
    }

    @GetMapping(value = "/find-client-byNickname")
    public ResponseEntity<List<ClientGetDTO>> findByNickname(@RequestParam String nickname) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET findByNickname()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByNickname(nickname));
    }

    @GetMapping(value = "/find-client-byCpf/{cpf}")
    public ResponseEntity<ClientGetDTO> findClientByCpf(@PathVariable(value = "cpf") String cpf) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET findClientByCpf()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByCpf(cpf));
    }

    @DeleteMapping(value = "/delete-client-byId/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" DELETE deleteClientById()"));
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Hidden
    @PutMapping(value = "/updated-client/{id}")
    public ResponseEntity<Void> updatedClient(@PathVariable Integer id, @RequestBody ClientPostDTO clientPostDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" PUT updateClient()"));
        clientService.updatedClient(id, clientPostDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/update-client/{id}")
    public ResponseEntity<Optional<ClientGetDTO>> updateClient(@PathVariable Integer id, @RequestBody ClientPutDTO clientPutDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" PATCH updateClient()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(id, clientPutDTO));
    }

}
