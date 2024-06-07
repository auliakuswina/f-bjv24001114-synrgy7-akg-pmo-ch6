package com.example.batch7.ch4.repository;

import com.example.batch7.ch4.entity.OrderDetail;
import com.example.batch7.ch4.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {
    @Query("From OrderDetail u")
    public Page<OrderDetail> getAllDataPage(Pageable pageable);
}
