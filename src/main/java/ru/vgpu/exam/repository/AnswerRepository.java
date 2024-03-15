package ru.vgpu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vgpu.exam.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}