package com.estore.api.estoreapi.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.Model.User;

@RestController
@RequestMapping("user")

public class UserController {
    @PostMapping("")
    @ResponseBody
    public boolean doLogin(@RequestBody User body){
        boolean admin=true;
        admin=admin&body.getName().equals("admin");
        admin=admin&body.getPassword().equals("admin");
        if(admin){
            return true;
        }
        else
            return false;
    }
}
