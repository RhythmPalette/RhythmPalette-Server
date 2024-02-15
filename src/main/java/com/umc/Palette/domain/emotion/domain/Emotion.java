package com.umc.Palette.domain.emotion.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "emotion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Emotion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long emotionId;

    @Column(name = "name")
    private String emotionName;
}
