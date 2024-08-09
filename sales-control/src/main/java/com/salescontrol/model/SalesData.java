package com.salescontrol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_sales_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal totalValue;

    private Integer totalOrderQuantity;

    private Integer quantityClients;

//  Variável para pedido mais solicitado
    private Double mostRequested;
    private List<Double> listMostRequested = new ArrayList<>();

}
