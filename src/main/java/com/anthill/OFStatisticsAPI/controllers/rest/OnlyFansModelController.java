package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansAccount;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.exceptions.ResourceAlreadyExists;
import com.anthill.OFStatisticsAPI.exceptions.ResourceNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.OnlyFansAccountRepos;
import com.anthill.OFStatisticsAPI.repos.OnlyFansModelRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OnlyFansModel")
@RequestMapping("/onlyFansModel")
@RestController
public class OnlyFansModelController extends AbstractController<OnlyFansModel, OnlyFansModelRepos> {

    private final OnlyFansAccountRepos accountRepos;

    protected OnlyFansModelController(OnlyFansModelRepos repos, OnlyFansAccountRepos accountRepos) {
        super(repos);
        this.accountRepos = accountRepos;
    }

    @PostMapping("{id}/account")
    public OnlyFansAccount saveAccount(@PathVariable("id") long id, @RequestBody OnlyFansAccount account) throws ResourceNotFoundedException {
        var modelOptional = repos.findById(id);

        return modelOptional
                .map(model -> {
                    account.setModel(model);
                    return accountRepos.save(account);
                })
                .orElseThrow(
                        ResourceNotFoundedException::new);

    }
}
