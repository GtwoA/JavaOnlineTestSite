package ru.gamzin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamzin.test.model.Answer;
import ru.gamzin.test.model.Question;
import ru.gamzin.test.service.AnswerService;
import ru.gamzin.test.service.QuestionService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    @Autowired private AnswerService answerService;
    @Autowired private QuestionService questionService;

    @GetMapping("/add/{questionId}")
    public String showAddAnswerForm(@PathVariable Long questionId, Model model) {
        Answer answer = new Answer();
        Question question = questionService.findById(questionId).orElseThrow();

        answer.setQuestion(question);

        model.addAttribute("answer", answer);
        return "add_answer";
    }

    @PostMapping("/add")
    public String addAnswer(@ModelAttribute Answer answer) {
        answerService.save(answer);
        return "redirect:/tests";
    }
}


