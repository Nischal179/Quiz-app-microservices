package com.nischal.quiz_service.service;

import com.nischal.quiz_service.dao.QuizDao;
import com.nischal.quiz_service.feign.QuizInterface;
import com.nischal.quiz_service.model.QuestionWrapper;
import com.nischal.quiz_service.model.Quiz;
import com.nischal.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionsIds = quiz.get().getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionsIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
//        List<Question> questions = quiz.get().getQuestions();
        int correctAnswer = 0;
//        int index = 0;
//        for (Response response : responses) {
//            if (response.getResponse().equals(questions.get(index).getRightAnswer()))
//                correctAnswer++;
//
//            index++;
//        }
        return new ResponseEntity<>(correctAnswer, HttpStatus.OK);
    }
}
