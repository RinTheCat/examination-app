package ru.vgpu.exam.dto;

import lombok.Builder;
import lombok.Data;
import ru.vgpu.exam.domain.Answer;

@Data
@Builder
public class AnswerDto {

    private Long id;

    private String text;

    public static AnswerDto toDto(Answer from) {
        return AnswerDto.builder()
                .id(from.getId())
                .text(from.getText())
                .build();
    }
}
