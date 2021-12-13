package com.example.anime1.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "favorite")
public class Favorite {

    public UUID userid;
    public UUID animeid;
}
