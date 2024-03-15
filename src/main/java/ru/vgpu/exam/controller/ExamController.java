package ru.vgpu.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vgpu.api.CheckResponse;
import ru.vgpu.exam.domain.Level;
import ru.vgpu.exam.dto.QuestionDto;
import ru.vgpu.exam.service.ExamService;

import java.util.List;
import java.util.Set;

@RestController("/api")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/level")
    public Level[] getAvailableLevels() {
        return Level.values();
    }

    @GetMapping("/task")
    public List<QuestionDto> getQuestions(@RequestParam Level level,
                                          @RequestParam(required = false, defaultValue = "1") Integer total) {
        return examService.getQuestions(level, total);
    }

    @PostMapping("/task/check")
    public CheckResponse createNewAccount(@RequestBody Set<Long> answerIds) {
        return examService.checkQuestions(answerIds);
    }
}
