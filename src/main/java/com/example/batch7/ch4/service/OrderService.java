package com.example.batch7.ch4.service;

import com.example.batch7.ch4.entity.Order;
import com.example.batch7.ch4.entity.User;

import java.util.Map;

public interface OrderService {
    Map list();

    Map edit(Order request);
    Map delete(Long id);
    Map save(Order request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
