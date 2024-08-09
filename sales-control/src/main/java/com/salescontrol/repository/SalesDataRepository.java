package com.salescontrol.repository;

import com.salescontrol.model.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDataRepository extends JpaRepository<SalesData, Integer> {
}
