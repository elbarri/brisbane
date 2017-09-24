package org.diaz.brisbane.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Choice implements Serializable{
	private static final long serialVersionUID = -5582524365388533506L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String choice;
	private int votes;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
	@JsonIgnore
	private Question question;

	public Question getQuestion() {
	    return question;
	}
	public Choice(String choice, int votes) {
		super();
		this.choice = choice;
		this.votes = votes;
	}
	public Choice(String choice, int votes, Question question) {
		super();
		this.choice = choice;
		this.votes = votes;
		this.question = question;
	}
	
	public Choice(){}
	
	public String getChoice() {
		return choice;
	}

	public int getVotes() {
		return votes;
	}
}
