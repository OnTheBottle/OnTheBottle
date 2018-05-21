package com.bottle.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table
public class UserOnline {

    @Id
    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private boolean online;
}

