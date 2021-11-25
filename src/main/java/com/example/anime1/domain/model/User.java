package com.example.anime1.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "usser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;
    public String username;
    public String password;
    public String role;
    public boolean enabled;
}