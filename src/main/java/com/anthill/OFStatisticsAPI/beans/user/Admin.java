package com.anthill.OFStatisticsAPI.beans.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("admin")
public class Admin extends AbstractUser {
}
