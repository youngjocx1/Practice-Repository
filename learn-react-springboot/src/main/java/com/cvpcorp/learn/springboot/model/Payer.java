package com.cvpcorp.learn.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payer {

    @Id
    public String id;
}
