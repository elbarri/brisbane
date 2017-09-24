package org.diaz.brisbane;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.diaz.brisbane.model.Choice;
import org.diaz.brisbane.model.Question;
import org.diaz.brisbane.repo.ChoicesRepo;
import org.diaz.brisbane.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrisbaneApplication {

	@Autowired
	QuestionRepo questionsRepo;
	@Autowired
	ChoicesRepo choicesRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BrisbaneApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner initializeDb(){
        return (args) -> {
        	questionsRepo.deleteAll();
        	choicesRepo.deleteAll();
        	
            //Insert one initial question
            Question question = new Question("Favourite programming language?", new Date());
            
            List<Choice> choices = new LinkedList<>();
            choices.add(new Choice("Swift", 2048, question));
            choices.add(new Choice("Python", 1024, question));
            choices.add(new Choice("Objective-C", 512, question));
            choices.add(new Choice("Ruby", 256, question));
            
            questionsRepo.save(question);
            choicesRepo.save(choices);
            
        };
    }
}
