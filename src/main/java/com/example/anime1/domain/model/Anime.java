package com.example.anime1.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String name;
    public String description;
    public String type;
    public int year_release;
    public String image;

    @ManyToMany
    @JoinTable(name = "favorite", joinColumns = @JoinColumn(name = "id"))
    public Set<User> favoritedby;
}