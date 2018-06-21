package fr.epita.quiz.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class MCQChoice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String choice;
	private boolean valid;

	@Column(name = "MCQCHOICE_ORDER")
	private int order;

	@ManyToOne
	private Question question;

	public MCQChoice() {

	}

	public String getChoice() {
		return choice;
	}

	/**
	 * @param choice
	 *            the choice to set
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * TODO to be removed
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

}
