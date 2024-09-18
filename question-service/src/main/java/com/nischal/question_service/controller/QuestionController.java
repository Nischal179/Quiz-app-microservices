package com.nischal.question_service.controller;


import com.nischal.question_service.model.Question;
import com.nischal.question_service.model.QuestionWrapper;
import com.nischal.question_service.model.Response;
import com.nischal.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // generating questions
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>>getQuestionsForQuiz(@RequestParam String category,
                                                            @RequestParam Integer numberOfQuestions) {
        return questionService.getAllQuestionsForQuiz(category, numberOfQuestions);
    }

    // getQuestions (questionId)
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromId(questionIds);
    }

    // getScore
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {

        return questionService.getScore(responses);
    }
}
