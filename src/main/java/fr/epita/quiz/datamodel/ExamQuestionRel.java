package fr.epita.quiz.datamodel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * <h3>Description</h3>
 * <p>ExamQuestionRel is a relationship object that links exams with questions.</p>
 */
@SuppressWarnings("serial")
@Entity
public class ExamQuestionRel implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	@ManyToOne
	Question question;
	
	@ManyToOne
	Exam exam;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * 		 the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 * 		 the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the exam
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * @param exam
	 * 		 the exam to set
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}


}
