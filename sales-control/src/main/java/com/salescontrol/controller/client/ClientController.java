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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final ClientService clientService;
    private final DateUtil dateUtil;

    @ApiResponse(responseCode = "201 - Created", description = "Deve retornar (201 - Created) ao salvar no banco de dados")
    @PostMapping(value = "/save")
    public ResponseEntity<ClientPostDTO> saveClient(@RequestBody @Valid ClientPostDTO clientPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientPost));
    }

    @ApiResponse(responseCode = "201 - Created", description = "Deve retornar (201 - Created) ao salvar no banco de dados")
    @PostMapping(value = "/save-multiple")
    public ResponseEntity<List<ClientPostDTO>> saveMultipleClients(
            @RequestBody List<ClientPostDTO> multipleClients) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveMultipleClients(multipleClients));
    }

    @PostMapping(value = "/save-with-address")
    public ResponseEntity<ClientForAddressPostDTO> saveClientWithAddress(
            @RequestBody ClientForAddressPostDTO clientWithAddress) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClientWithAddress(clientWithAddress));
    }

    @GetMapping(value = "/list-entity")
    public ResponseEntity<List<Client>> listClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClients());
    }

    @GetMapping(value = "/list-dto")
    public ResponseEntity<List<ClientGetDTO>> listClientsDTO() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClientsDTO());
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<ClientGetDTO>> clientsPageDTO(@PageableDefault(page = 0, size = 10,
    direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.clientsPageDTO(pageable));
    }

    @GetMapping(value = "/list-with-addresses")
    public ResponseEntity<List<ClientForAddressGetDTO>> listClientsWithAddresses() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClientsWithAddresses());
    }

    @GetMapping(value = "/find-byName")
    public ResponseEntity<ClientGetDTO> findClientByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByName(name));
    }

    @GetMapping(value = "/find-byNickname")
    public ResponseEntity<List<ClientGetDTO>> findByNickname(@RequestParam String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByNickname(nickname));
    }

    @GetMapping(value = "/find-byCpf/{cpf}")
    public ResponseEntity<ClientGetDTO> findClientByCpf(@PathVariable(value = "cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByCpf(cpf));
    }

    @DeleteMapping(value = "/delete-byId/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Hidden
    @PutMapping(value = "/updated/{id}")
    public ResponseEntity<Void> updatedClient(@PathVariable Integer id, @RequestBody ClientPostDTO clientPostDTO) {
        clientService.updatedClient(id, clientPostDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ClientGetDTO> updateClient(@PathVariable Integer id, @RequestBody ClientPutDTO clientPutDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(id, clientPutDTO));
    }

}
