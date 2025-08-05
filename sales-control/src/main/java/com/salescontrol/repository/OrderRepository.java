package com.salescontrol.repository;

import com.salescontrol.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    Para as quantidades mais solicitadas
    @Query("SELECT o.quantity, COUNT(o) FROM Order o GROUP BY o.quantity ORDER BY COUNT(o) DESC")
    List<Object[]> findMostRequestedQuantities();

//    @Query("SELECT COALESCE(SUM(o.idOrder), 0) FROM ORDER o")
//    Integer sumTotalOrders();

    @Query("SELECT COUNT(o) FROM Order o")
    Integer sumTotalOrders();

    @Query("SELECT COALESCE(SUM(o.quantity), 0) FROM Order o")
    Double sumQuantities();

    @Query("SELECT COALESCE(SUM(o.price), 0) FROM Order o")
    BigDecimal sumPrices();

}