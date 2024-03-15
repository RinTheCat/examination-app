package ru.vgpu.exam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(schema = "exam", name = "answer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "text")
    String text;

    @Column(name = "is_correct")
    Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", nullable=false)
    Question question;

    public Answer(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        Answer a = (Answer) o;

        return Objects.equals(this.id, a.id);
    }
}
