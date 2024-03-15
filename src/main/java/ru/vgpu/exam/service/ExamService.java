package ru.vgpu.exam.service;

import ru.vgpu.api.CheckResponse;
import ru.vgpu.exam.domain.Level;
import ru.vgpu.exam.dto.QuestionDto;

import java.util.List;
import java.util.Set;

public interface ExamService {

    List<QuestionDto> getQuestions(Level level, Integer total);

    CheckResponse checkQuestions(Set<Long> answerIds);

}
