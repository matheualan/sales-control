//package com.salescontrol.creator;
//
//import com.salescontrol.dto.client.ClientGetDTO;
//import com.salescontrol.dto.client.ClientPostDTO;
//import com.salescontrol.dto.client.forAddress.ClientForAddressGetDTO;
//import com.salescontrol.dto.client.forAddress.ClientForAddressPostDTO;
//import com.salescontrol.model.Client;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//public class ClientCreator {
//
//    public static Client createValidClient() {
//        return Client.builder()
//                .idClient(1)
//                .name("Cliente 1")
//                .nickname("Apelido 1")
//                .cpf("CPF 1")
//                .totalQuantity(0.0)
//                .totalPurchased(BigDecimal.ZERO)
//                .build();
//    }
//
//    public static Client createValidClient2() {
//        return Client.builder()
//                .idClient(2)
//                .name("Cliente 2")
//                .nickname("Apelido 2")
//                .cpf("CPF 2")
//                .totalQuantity(0.0)
//                .totalPurchased(BigDecimal.ZERO)
//                .build();
//    }
//
//    public static ClientPostDTO clientPostDTO() {
//        return ClientPostDTO.builder()
//                .name("Cliente DTO 1")
//                .nickname("Apelido DTO 1")
//                .cpf("CPF DTO 1")
//                .build();
//    }
//
//    public static ClientPostDTO clientPostDTOTest() {
//        return ClientPostDTO.builder()
//                .name("Cliente DTO 1")
//                .nickname("Apelido DTO 1")
//                .cpf("CPF DTO 1")
//                .build();
//    }
//
//    public static ClientPostDTO clientPostDTO2() {
//        return ClientPostDTO.builder()
//                .name("Cliente DTO 2")
//                .nickname("Apelido DTO 2")
//                .cpf("CPF DTO 2")
//                .build();
//    }
//
//    public static ClientForAddressPostDTO clientForAddressPostDTO() {
//        return ClientForAddressPostDTO.builder()
//                .name("Name client with address")
//                .nickname("Nickname client with address")
//                .cpf("CPF client with address")
//                .build();
//    }
//
//    public static ClientForAddressGetDTO clientForAddressGetDTO() {
//        return ClientForAddressGetDTO.builder()
//                .name("Name client with address")
//                .nickname("Nickname client with address")
//                .cpf("CPF client with address")
//                .build();
//    }
//
//    public static ClientGetDTO clientGetDTO() {
//        return ClientGetDTO.builder()
//                .name("Name client get DTO")
//                .nickname("Nickname client get DTO")
//                .cpf("CPF client get DTO")
//                .createdAt(LocalDateTime.now())
//                .build();
//    }
//
//}
