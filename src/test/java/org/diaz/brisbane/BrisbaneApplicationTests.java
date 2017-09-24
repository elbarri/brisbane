package org.diaz.brisbane;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.diaz.brisbane.controller.QuestionContext;
import org.diaz.brisbane.model.Choice;
import org.diaz.brisbane.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BrisbaneApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	static final String DEFAULT_QUESTION = "Favourite programming language?";
	Set<String> choiceNames = new HashSet<>();
	
	@Before
	public void setUp() {
        choiceNames.add("Swift");
        choiceNames.add("Python");
        choiceNames.add("Objective-C");
        choiceNames.add("Ruby");
        
	}

	public void testInitialData() {

		ResponseEntity<Question[]> responseEntity = restTemplate.getForEntity("/questions", Question[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		List<Question> questions = Arrays.asList(responseEntity.getBody());
		assertEquals(1, questions.size());
		
		Question question = questions.get(0);
		
		assertEquals(DEFAULT_QUESTION, question.getQuestion());
		assertEquals(4, question.getChoices().size());
		for(Choice c : question.getChoices()) {
			assertTrue(choiceNames.contains(c.getChoice()));
			choiceNames.remove(c);
		}
	}
	
	@Test
	public void testCreateQuestion() {
		
		testInitialData();

		Set<String> choices = new HashSet<>();
		choices.add("choice1");
		choices.add("choice2");
		
		ResponseEntity<Question> responseEntity = restTemplate.postForEntity("/questions", 
				new QuestionContext("ASD", choices), Question.class);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		Question question = responseEntity.getBody();
		
		assertEquals("ASD", question.getQuestion());
		assertEquals(2, question.getChoices().size());
		for(Choice c : question.getChoices()) {
			assertTrue(choices.contains(c.getChoice()));
			assertEquals(0, c.getVotes());
			choices.remove(c);
		}
		
		testReturnBothQuestions();
	}

	protected void testReturnBothQuestions() {
		ResponseEntity<Question[]> responseEntity = restTemplate.getForEntity("/questions", Question[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		List<Question> questions = Arrays.asList(responseEntity.getBody());
		assertEquals(2, questions.size());
	}

}
