package com.example.batch7.ch4.service;

import com.example.batch7.ch4.entity.Order;
import com.example.batch7.ch4.entity.OrderDetail;
import com.example.batch7.ch4.entity.User;

import java.util.Map;

public interface OrderDetailService {
    Map list();

    Map edit(OrderDetail request);
    Map delete(Long id);
    Map save(OrderDetail request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
