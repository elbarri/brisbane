package org.diaz.brisbane.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Question implements Serializable{
	private static final long serialVersionUID = -5500367890300011227L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String question;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date published_at;
	
	@OneToMany(mappedBy="question", cascade = CascadeType.ALL)
	private Collection<Choice> choices;
	
	public Question(){}
	
	public String getQuestion() {
		return question;
	}

	public Date getPublished_at() {
		return published_at;
	}

	public Collection<Choice> getChoices() {
		return choices;
	}

	
	public Question(String question, Date published_at) {
		super();
		this.question = question;
		this.published_at = published_at;
	}

	public void setChoices(Collection<Choice> choices) {
		this.choices = choices;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", published_at=" + published_at + ", choices="
				+ choices + "]";
	}
	
}
