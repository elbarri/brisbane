package org.diaz.brisbane.controller;

import java.io.Serializable;
import java.util.Collection;

public class QuestionContext implements Serializable {
	private static final long serialVersionUID = 7236201025108492378L;
	private String question;
	
	public QuestionContext() {
		super();
	}
	public QuestionContext(String question, Collection<String> choices) {
		super();
		this.question = question;
		this.choices = choices;
	}
	private Collection<String> choices;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Collection<String> getChoices() {
		return choices;
	}
	public void setChoices(Collection<String> choices) {
		this.choices = choices;
	}
}
