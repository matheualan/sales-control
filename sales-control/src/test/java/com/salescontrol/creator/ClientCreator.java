package com.salescontrol.creator;

import com.salescontrol.model.Client;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCreator {

    public static Client createValidClient() {
        return Client.builder()
                .idClient(1)
                .name("Cliente 1")
                .nickname("Apelido 1")
                .cpf("CPF 1")
                .totalQuantity(0.0)
                .totalPurchased(BigDecimal.ZERO)
                .build();
    }

    public static Client createValidClient2() {
        return Client.builder()
                .idClient(2)
                .name("Cliente 2")
                .nickname("Apelido 2")
                .cpf("CPF 2")
                .totalQuantity(0.0)
                .totalPurchased(BigDecimal.ZERO)
                .build();
    }

    public static Client createSimpleClient() {
        return Client.builder()
                .idClient(3)
                .name("Cliente 3")
                .nickname("Apelido 3")
                .cpf("CPF 3")
                .totalQuantity(0.0)
                .totalPurchased(BigDecimal.ZERO)
                .build();
    }

}
