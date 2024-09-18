package com.nischal.quiz_service.dto;

import lombok.Data;

@Data
public class QuizDto {

    String category;
    Integer numberOfQuestions;
    String title;
}
