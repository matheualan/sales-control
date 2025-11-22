package com.salescontrol.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Comentar essa anotacao para poder salvar novos clientes na API - Descomentar para realizar os testes
@JsonIgnoreProperties(value = {"totalOrders", "totalQuantity", "totalPurchased"})
public class ClientPostDTO {

    @NotBlank(message = "O campo 'name' não pode ser branco ou nulo.")
    @Size(min = 3, max = 255, message = "O campo 'name' deve conter entre 3 a 255 caracteres.")
    private String name;

    private String nickname;

//    @NotBlank(message = "O campo 'CPF' não pode ser branco ou nulo.")
    @Size(min = 11, max = 11, message = "O campo 'CPF' deve conter 11 números.")
    private String cpf;

    private Integer totalOrders = 0;

    private Double totalQuantity = 0.0;

    @Builder.Default
    private BigDecimal totalPurchased = BigDecimal.ZERO;

}