package com.anthill.OFStatisticsAPI.beans.user;


import com.anthill.OFStatisticsAPI.beans.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user")
@NoArgsConstructor
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_role",
        discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractUser extends AbstractEntity {

    private String login, password;
}
