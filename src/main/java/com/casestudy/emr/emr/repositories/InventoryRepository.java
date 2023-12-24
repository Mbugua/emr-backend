package com.casestudy.emr.emr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.emr.emr.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByBatchNo(String batchNo);

    List<Inventory> findByTitle(String title);

    List<Inventory> findByExpirationDate(String expirationDate);
}
