package ru.gamzin.test.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamzin.test.QuestionForm;
import ru.gamzin.test.model.Answer;
import ru.gamzin.test.model.Question;
import ru.gamzin.test.model.Test;
import ru.gamzin.test.repository.QuestionRepository;
import ru.gamzin.test.service.AnswerService;
import ru.gamzin.test.service.QuestionService;
import ru.gamzin.test.service.TestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired private QuestionService questionService;
    @Autowired private TestService testService;
    @Autowired private AnswerService answerService;

    @GetMapping("/add/{testId}")
    public String showAddQuestionForm(@PathVariable Long testId, Model model) {
        QuestionForm form = new QuestionForm();
        form.setTestId(testId);

        model.addAttribute("questionForm", form);
        return "add_question";
    }

    @PostMapping("/add")
    public String addQuestion(@ModelAttribute QuestionForm questionForm) {
        Test test = testService.findById(questionForm.getTestId()).orElseThrow();

        Question question = new Question();
        question.setContent(questionForm.getContent());
        question.setTest(test);
        question = questionService.save(question);

        Answer correct = new Answer();
        correct.setContent(questionForm.getCorrectAnswer());
        correct.setCorrect(true);
        correct.setQuestion(question);
        answerService.save(correct);

        for (String wrongText : List.of(
                questionForm.getWrongAnswer1(),
                questionForm.getWrongAnswer2(),
                questionForm.getWrongAnswer3()
        )) {
            Answer wrong = new Answer();
            wrong.setContent(wrongText);
            wrong.setCorrect(false);
            wrong.setQuestion(question);
            answerService.save(wrong);
        }

        return "redirect:/tests/" + test.getId();
    }
}

