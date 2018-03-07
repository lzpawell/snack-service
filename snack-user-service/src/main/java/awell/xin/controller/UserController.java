package awell.xin.controller;

import awell.xin.entities.UserEntity;
import awell.xin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/aaa")
    public String aaa(UserEntity aaa){
        return aaa.getUserId();
    }

}
