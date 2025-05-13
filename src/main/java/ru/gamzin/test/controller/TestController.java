package ru.gamzin.test.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.gamzin.test.model.Answer;
import ru.gamzin.test.model.Question;
import ru.gamzin.test.model.Test;
import ru.gamzin.test.service.AnswerService;
import ru.gamzin.test.service.QuestionService;
import ru.gamzin.test.service.TestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/tests")
public class TestController {
    @Autowired private TestService testService;
    @Autowired private QuestionService questionService;
    @Autowired private AnswerService answerService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("tests", testService.findAll());
        return "index";
    }

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/tests";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("test", new Test());
        return "create_test";
    }

    @PostMapping("/create")
    public String createTest(@ModelAttribute Test test) {
        testService.save(test);
        return "redirect:/tests";
    }


    // Просмотр теста
    @GetMapping("/{id}")
    public String viewTest(@PathVariable Long id, Model model) {
        Test test = testService.findById(id).orElseThrow();
        model.addAttribute("test", test);
        return "view_tests";
    }

    // Форма прохождения теста
    @GetMapping("/take/{id}")
    public String takeTest(@PathVariable Long id, Model model) {
        Test test = testService.findById(id).orElseThrow();

        for (Question question : test.getQuestions()) {
            List<Answer> shuffledAnswers = new ArrayList<>(question.getAnswers());
            Collections.shuffle(shuffledAnswers);
            question.setAnswers(shuffledAnswers);
        }

        model.addAttribute("test", test);
        return "take_test";

    }

    // Обработка результатов
    @PostMapping("/submit")
    public String submitTest(@RequestParam MultiValueMap<String, String> formData, Model model) {
        int correctCount = 0;

        for (String key : formData.keySet()) {
            if (key.startsWith("question_")) {
                List<String> selectedIds = formData.get(key);
                if (selectedIds != null) {
                    for (String answerIdStr : selectedIds) {
                        Long answerId = Long.parseLong(answerIdStr);
                        Answer answer = answerService.findById(answerId).orElse(null);
                        if (answer != null && answer.isCorrect()) {
                            correctCount++;
                        }
                    }
                }
            }
        }

        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalAnswers", formData.size());
        return "test_result";
    }

    @PostMapping("/delete/{id}")
    public String deleteTest(@PathVariable Long id) {
        testService.deleteById(id);
        return "redirect:/tests";
    }
}

