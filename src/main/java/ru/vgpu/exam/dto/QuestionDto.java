package ru.vgpu.exam.dto;

import lombok.Builder;
import lombok.Data;
import ru.vgpu.exam.domain.Question;

import java.util.List;

@Data
@Builder
public class QuestionDto {

    private Long id;

    private String text;

    private List<AnswerDto> answers;

    public static QuestionDto toDto(Question from) {
        return QuestionDto.builder()
                .id(from.getId())
                .text(from.getText())
                .answers(from.getAnswers()
                        .stream()
                        .map(AnswerDto::toDto)
                        .toList())
                .build();
    }

}
