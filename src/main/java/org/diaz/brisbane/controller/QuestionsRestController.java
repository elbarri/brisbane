package org.diaz.brisbane.controller;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.diaz.brisbane.model.Choice;
import org.diaz.brisbane.model.Question;
import org.diaz.brisbane.repo.ChoicesRepo;
import org.diaz.brisbane.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QuestionsRestController {
	@Autowired
    private QuestionRepo questionsRepo;
	@Autowired
	ChoicesRepo choicesRepo;

    @GetMapping("questions")
    public ResponseEntity<Collection<Question>> getAllQuestions(){
    	return new ResponseEntity<>((Collection<Question>)questionsRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("questions")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionContext questionContext) {
        Question question = new Question(questionContext.getQuestion(), new Date());
        List<Choice> choicesList = new LinkedList<>();
        questionContext.getChoices().forEach(s->choicesList.add(new Choice(s, 0, question)));
        question.setChoices(choicesList);
        
        questionsRepo.save(question);
        choicesRepo.save(choicesList);
        
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }
}
