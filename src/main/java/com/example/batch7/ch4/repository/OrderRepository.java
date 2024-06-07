package com.example.batch7.ch4.repository;

import com.example.batch7.ch4.entity.Order;
import com.example.batch7.ch4.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query("From Order u")
    public Page<Order> getAllDataPage(Pageable pageable);
}
