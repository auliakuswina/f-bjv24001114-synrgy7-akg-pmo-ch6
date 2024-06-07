package com.example.batch7.ch4.service.impl;

import com.example.batch7.ch4.entity.Employee;
import com.example.batch7.ch4.entity.User;
import com.example.batch7.ch4.repository.UserRepository;
import com.example.batch7.ch4.service.UserService;
import com.example.batch7.ch4.utils.Response;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceImplUser implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Response response;

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map edit(User request) {
        try {
            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<User> getId = userRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            User edit = getId.get();
            edit.setEmail_address(request.getEmail_address());
            edit.setUsername(request.getUsername());

            return response.sukses(userRepository.save(edit));
        }catch (Exception e){
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            if(response.chekNull(id)){
                return  response.error("Id is required.",402);
            }

            Optional<User> getId = userRepository.findById(id);
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }
            userRepository.deleteById(id);
            return response.sukses(null);
        } catch (Exception e) {
            return response.error(e.getMessage(), 500);
        }
    }

    @Override
    public Map save(User request) {
        if(response.chekNull(request.getEmail_address())) {
            return response.error("Email address is required.", 402);
        }
        if(response.chekNull(request.getUsername())) {
            return response.error("Username is required.", 402);
        }
        if(response.chekNull(request.getPassword())) {
            return response.error("Password is required.", 402);
        }
        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        request.setPassword(hashedPassword);

        return response.sukses(userRepository.save(request));
    }

    @Override
    public Map getById(Long id) {
        Map map = new HashMap();
        Optional<User> getId = userRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<User> list = userRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
