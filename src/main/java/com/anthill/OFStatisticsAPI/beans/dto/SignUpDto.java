package com.anthill.OFStatisticsAPI.beans.dto;

import com.anthill.OFStatisticsAPI.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class SignUpDto {

    private String login, password;
}
