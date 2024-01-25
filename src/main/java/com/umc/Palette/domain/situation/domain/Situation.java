package com.umc.Palette.domain.situation.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "situation")
public class Situation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "situation_id")
    private Long situationId;

    @Column(name = "name")
    private String situationName;
}
