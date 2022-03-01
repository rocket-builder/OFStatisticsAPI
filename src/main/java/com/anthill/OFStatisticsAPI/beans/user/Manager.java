package com.anthill.OFStatisticsAPI.beans.user;

import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("manager")
public class Manager extends AbstractUser {

    private boolean banned = false;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<OnlyFansModel> models = new ArrayList<>();

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Worker> workers = new ArrayList<>();
}
