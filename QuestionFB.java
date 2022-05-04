/**
 * Implementation of Question for Fill in the blank-type questions
 * 
 * @author pott6366
 *
 */
public class QuestionFB implements Question {
	
	/** Text content of the question */
	private String question;
	
	/** Answer value of the question */
	private String answer;
	
	/** Point value of the question */
	private int points;
	
	/**
	 * Creates a fill-in-the-blank question.
	 * @param question Question text
	 * @param answer Answer to the question
	 * @param points Point value of the question.
	 */
	public QuestionFB(String question, String answer, int points) {
		this.question = question;
		this.answer = answer;
		this.points = points;
	}
	
	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public boolean isCorrect(String ans) {
		return ans.toLowerCase().equals(answer.toLowerCase());
	}

	@Override
	public String getCorrectAns() {
		return answer;
	}

	@Override
	public int getPoints() {
		return points;
	}

}
