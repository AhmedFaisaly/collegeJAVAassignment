/*File Name:QuizMain.java
 * Author: Tao Wang 040857654
 * course:CST8284 300 Fall 2016
 * Assignment: 2
 * Date:Nov 27th,2016
 * Professor: David B Houtman
 * purpose: create a class to describe and handle QA Object
 */

package algonquin.cst8284.assignment2;

/**
 * This class extends QuesAns to describe and handle the QA members
 * @author Wang: David B Houtman;
 * @version 1.0
 * @since javac 1.8.0_102
 * 
 * 
 */
public class QA extends QuesAns  {

	
	private static final long serialVersionUID = 1L;
	private String question, explanation, author;
	private int correctAnswer, difficulty, points;
	private String[] answers;
	private boolean result;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCorrectAnswerNumber() {
		return correctAnswer;
	}

	public void setCorrectAnswerNumber(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = new String[answers.length];
		for (int i = 0; i < answers.length; i++)
			this.answers[i] = answers[i];
	}

	public void setResult(boolean b) {
		result = b;
	}

	public boolean isCorrect() {
		return result;
	}
	public QA(QA qa){
		
	}

	public QA(String q, String[] a, int correctAnswer, int difficulty, int points, String expl, String author) {
		setQuestion(q);
		setAnswers(a);
		setCorrectAnswerNumber(correctAnswer);
		setDifficulty(difficulty);
		setPoints(points);
		setExplanation(expl);
		setAuthor(author);
	}

}
