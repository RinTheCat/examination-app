package ru.vgpu.exam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Table(schema = "exam", name = "question")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "text")
    String text;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    Level level;

    @OneToMany(mappedBy="question", fetch = FetchType.EAGER)
    Set<Answer> answers;
}
