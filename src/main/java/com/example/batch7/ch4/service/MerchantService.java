package com.example.batch7.ch4.service;

import com.example.batch7.ch4.entity.Merchant;
import com.example.batch7.ch4.entity.User;

import java.util.Map;

public interface MerchantService {
    Map list();

    Map edit(Merchant request);
    Map delete(Long id);
    Map save(Merchant request);

    Map getById(Long id);

    Map pagination(int page, int size);
}
