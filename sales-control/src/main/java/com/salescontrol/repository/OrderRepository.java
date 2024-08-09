package com.salescontrol.repository;

import com.salescontrol.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o.quantity, COUNT(o) FROM Order o GROUP BY o.quantity ORDER BY COUNT(o) DESC")
    List<Object[]> findMostRequestedQuantities();

}
