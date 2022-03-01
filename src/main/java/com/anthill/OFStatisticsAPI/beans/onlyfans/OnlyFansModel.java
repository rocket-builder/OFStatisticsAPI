package com.anthill.OFStatisticsAPI.beans.onlyfans;

import com.anthill.OFStatisticsAPI.beans.AbstractEntity;
import com.anthill.OFStatisticsAPI.beans.user.Manager;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@JsonIgnoreProperties({"manager"})
public class OnlyFansModel extends AbstractEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<OnlyFansAccount> accounts;
}
