package com.logos.fulltank.dto;

import com.logos.fulltank.entity.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int age;

    private Role role;
}
