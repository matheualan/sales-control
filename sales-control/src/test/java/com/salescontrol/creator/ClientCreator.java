package com.salescontrol.creator;

import com.salescontrol.model.Client;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCreator {

    public static Client createValidClient() {
        return Client.builder()
                .idClient(1)
                .name("Cliente criado com sucesso")
                .nickname("Apelido criado com sucesso")
                .cpf("CPF criado com sucesso")
                .totalQuantity(0.0)
                .totalPurchased(BigDecimal.ZERO)
                .build();
    }

}
