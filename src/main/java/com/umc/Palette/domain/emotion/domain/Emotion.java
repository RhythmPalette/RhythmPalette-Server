package com.umc.Palette.domain.emotion.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "emotion")
public class Emotion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long emotionId;

    @Column(name = "name")
    private String emotionName;
}
