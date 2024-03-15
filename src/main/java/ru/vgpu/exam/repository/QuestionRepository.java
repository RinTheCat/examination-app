package ru.vgpu.exam.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vgpu.exam.domain.Level;
import ru.vgpu.exam.domain.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByLevel(Level level, Pageable page);

}