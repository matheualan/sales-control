package com.salescontrol.controller;

import com.salescontrol.dto.client.ClientGetDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientControllerTestWithRestTemplate {

    @Autowired
    TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testEndpoint() {
        String url = "http://localhost:8082/client/find-client-byCpf/{cpf}";

        ResponseEntity<ClientGetDTO> forEntity = testRestTemplate.getForEntity(url, ClientGetDTO.class, "String");

        Assertions.assertThat(forEntity.getBody()).isNotNull();
//        Assertions.assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
