package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.User;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.exceptions.IncorrectPasswordException;
import com.anthill.OFStatisticsAPI.exceptions.UserNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.UserRepos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.MD5;

@RequestMapping("/user")
@RestController
public class UserController extends AbstractController<User, UserRepos> {

    protected UserController(UserRepos repos) {
        super(repos);
    }

    @PostMapping("/login")
    public User login(@RequestBody User auth) throws UserNotFoundedException, IncorrectPasswordException {
        var user = repos.findByLogin(auth.getLogin());

        if (user.isEmpty()){
            throw new UserNotFoundedException();
        }
        if(!user.get().getPassword().equals(MD5.getHash(auth.getPassword()))){
            throw new IncorrectPasswordException();
        }

        return user.get();
    }
}
