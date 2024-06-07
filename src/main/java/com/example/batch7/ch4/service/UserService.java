package com.example.batch7.ch4.service;

import com.example.batch7.ch4.entity.User;

import java.util.Map;

public interface UserService {
    Map list();

    Map edit(User request);
    Map delete(Long id);
    Map save(User request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
