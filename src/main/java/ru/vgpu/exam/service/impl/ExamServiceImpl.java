package ru.vgpu.exam.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vgpu.api.CheckResponse;
import ru.vgpu.api.CheckResponse.Score;
import ru.vgpu.api.Match;
import ru.vgpu.exam.domain.Answer;
import ru.vgpu.exam.domain.Level;
import ru.vgpu.exam.dto.QuestionDto;
import ru.vgpu.exam.repository.AnswerRepository;
import ru.vgpu.exam.repository.QuestionRepository;
import ru.vgpu.exam.service.ExamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<QuestionDto> getQuestions(Level level, Integer total) {
        return questionRepository.findAllByLevel(level, Pageable.ofSize(total))
                .stream()
                .map(QuestionDto::toDto)
                .toList();
    }

    @Override
    public CheckResponse checkQuestions(Set<Long> answerIds) {
        final List<Answer> allAnswers = answerRepository.findAllById(answerIds);
        final List<Long> correctAnswers = allAnswers
                .stream()
                .filter(Answer::getIsCorrect)
                .map(Answer::getId)
                .toList();

        final CheckResponse response = new CheckResponse();
        response.setMatch(populateMatches(answerIds, allAnswers, correctAnswers));
        response.setTotal((long) allAnswers.size());
        response.setCorrect((long) correctAnswers.size());
        response.setScore(decideScore(allAnswers.size(), correctAnswers.size()));
        return response;
    }

    private List<Match> populateMatches(Set<Long> originalIds, List<Answer> all, List<Long> correct) {
        final List<Match> matches = new ArrayList<>();
        for (Long id : originalIds) {
            final Match match = new Match();
            match.setAnswerId(id);
            if (correct.contains(id)) {
                match.setStatus("CORRECT");
            } else if (all.contains(new Answer(id))) {
                match.setStatus("INCORRECT");
            } else match.setStatus("NOT FOUND");
            matches.add(match);
        }
        return matches;
    }

    private Score decideScore(int total, int correct) {
        return ((double) correct / total) * 100 >= 50
                ? Score.PASSED
                : Score.FAILED;
    }

}




