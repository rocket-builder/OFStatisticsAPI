package com.anthill.OFStatisticsAPI.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
public class User extends AbstractEntity {

    private String login, password;
}
