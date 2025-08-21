package com.salescontrol.repository;

import com.salescontrol.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT COUNT(o) FROM Order o")
    Integer sumTotalOrders();

    @Query("SELECT COALESCE(SUM(o.quantity), 0) FROM Order o")
    Double sumQuantities();

    @Query("SELECT COALESCE(SUM(o.price), 0) FROM Order o")
    BigDecimal sumPrices();

}