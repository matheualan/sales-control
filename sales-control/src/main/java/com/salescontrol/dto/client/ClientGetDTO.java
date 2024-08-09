package com.salescontrol.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientGetDTO {

    @NotBlank(message = "O campo 'name' não pode ser branco ou nulo.")
    @Size(min = 3, max = 255, message = "O campo 'name' deve conter entre 3 a 255 caracteres.")
    private String name;

    private String nickname;

    @NotBlank(message = "O campo 'CPF' não pode ser branco ou nulo.")
    @Size(min = 11, max = 11, message = "O campo 'CPF' deve conter 11 caracteres.")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

}
