package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.dto.SignUpDto;
import com.anthill.OFStatisticsAPI.beans.user.AbstractUser;
import com.anthill.OFStatisticsAPI.beans.user.Admin;
import com.anthill.OFStatisticsAPI.beans.user.Manager;
import com.anthill.OFStatisticsAPI.beans.user.Worker;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.enums.Role;
import com.anthill.OFStatisticsAPI.exceptions.*;
import com.anthill.OFStatisticsAPI.repos.AbstractUserRepos;
import com.anthill.OFStatisticsAPI.repos.AdminRepos;
import com.anthill.OFStatisticsAPI.repos.ManagerRepos;
import com.anthill.OFStatisticsAPI.repos.WorkerRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import security.MD5;

@Tag(name = "User")
@RequestMapping("/user")
@RestController
public class AbstractUserController extends AbstractController<AbstractUser, AbstractUserRepos> {

    private final AdminRepos adminRepos;
    private final WorkerRepos workerRepos;
    private final ManagerRepos managerRepos;

    protected AbstractUserController(AbstractUserRepos repos, AdminRepos adminRepos, WorkerRepos workerRepos,
                                     ManagerRepos managerRepos) {
        super(repos);
        this.adminRepos = adminRepos;
        this.workerRepos = workerRepos;
        this.managerRepos = managerRepos;
    }

    @PostMapping("/login")
    public AbstractUser login(@RequestBody AbstractUser auth) throws UserNotFoundedException, IncorrectPasswordException {
        var user = repos.findByLogin(auth.getLogin());

        if (user.isEmpty()){
            throw new UserNotFoundedException();
        }
        if(!user.get().getPassword().equals(MD5.getHash(auth.getPassword()))){
            throw new IncorrectPasswordException();
        }

        return user.get();
    }

    @PostMapping("/{role}/signUp")
    public AbstractUser signUp(@PathVariable("role") Role role, @RequestBody SignUpDto signUp)
            throws LoginAlreadyTakenException, IncorrectUserException {
        if(repos.existsByLogin(signUp.getLogin())){
            throw new LoginAlreadyTakenException();
        }

        var password = MD5.getHash(signUp.getPassword());

        switch (role){
            case ADMIN:{
                var admin = new Admin();
                admin.setLogin(signUp.getLogin());
                admin.setPassword(password);

                return adminRepos.save(admin);
            }
            case MANAGER:{
                var manager = new Manager();
                manager.setLogin(signUp.getLogin());
                manager.setPassword(password);

                return managerRepos.save(manager);
            }
            default:
                throw new IncorrectUserException();
        }
    }

    @PostMapping("/{id}/worker")
    public Worker saveWorker(@PathVariable("id") long id, @RequestBody Worker worker)
            throws ResourceNotFoundedException, LoginAlreadyTakenException {
        if(repos.existsByLogin(worker.getLogin())){
            throw new LoginAlreadyTakenException();
        }

        var managerOptional = managerRepos.findById(id);
        return managerOptional
                .map(manager -> {
                    worker.setManager(manager);
                    return workerRepos.save(worker);
                })
                .orElseThrow(ResourceNotFoundedException::new);
    }
}
