package com.example.batch7.ch4.controller;

import com.example.batch7.ch4.entity.User;
import com.example.batch7.ch4.service.UserService;
import com.example.batch7.ch4.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(value = {"/list-user"})
    public ResponseEntity<Map> getListUser() {
        return new ResponseEntity<Map>(response.sukses(userService.pagination(0, 1)), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public Map saveUser(@RequestBody User request) {
        Map map = new HashMap();
        try {
            logger.info("Request:", request);
            map = userService.save(request);
            return map;
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = "/edit/{id}")
    public Map editUser(@PathVariable Long id, @RequestBody User request) {
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request:", request);
            map = userService.edit(request);
            return map;
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return map;
        }
    }

    @GetMapping(value = "/list-user/{id}")
    public ResponseEntity<Map> getById(@PathVariable Long id) {
        Map map = new HashMap();
        try {
            map = userService.getById(id);
            return new ResponseEntity<Map>(response.sukses(map), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error get : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.REQUEST_TIMEOUT);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map> deleteUser(@PathVariable Long id) {
        try {
            logger.info("Request : ", id);
            userService.delete(id);
            return new ResponseEntity<Map>(response.sukses(null), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Error save : ", e.getMessage());
            return new ResponseEntity<Map>(response.error(null, 500), HttpStatus.OK);
        }
    }
}
